package ifTG.travelPlan.elasticsearch.repository;

import co.elastic.clients.elasticsearch._types.InlineScript;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.ScriptLanguage;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EDestinationRecommendRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public List<EDestination> findSightSeeingByUserVector(double[] userVector, Pageable pageable) {
        return findByUserVectorAndContentType(ContentType.Sightseeing.toString(), userVector, pageable);
    }
    public List<EDestination> findEventByUserVector(double[] userVector, Pageable pageable) {
        List<EDestination> byUserVectorAndContentType = findByUserVectorAndContentType(ContentType.Event_Performance_Festival.toString(), userVector, pageable);
        return findByUserVectorAndContentType(ContentType.Event_Performance_Festival.toString(), userVector, pageable);
    }
    public List<EDestination> findCulturalByUserVector(double[] userVector, Pageable pageable) {
        return findByUserVectorAndContentType(ContentType.Cultural_Facility.toString(), userVector, pageable);
    }
    public List<EDestination> findLeisureByUserVector(double[] userVector, Pageable pageable) {
        return findByUserVectorAndContentType(ContentType.LeisureSports.toString(), userVector, pageable);
    }
    public List<EDestination> findShoppingByUserVector(double[] userVector, Pageable pageable) {
        return findByUserVectorAndContentType(ContentType.Shopping.toString(), userVector, pageable);
    }
    public List<EDestination> findRestaurantByUserVector(double[] userVector, Pageable pageable) {
        return findByUserVectorAndContentType(ContentType.Restaurant.toString(), userVector, pageable);
    }

    private List<EDestination> findByUserVectorAndContentType(String field, double[] userVector, Pageable pageable){
        Script script = getScript(userVector);

        NativeQuery query = NativeQuery.builder()
                                       .withQuery(q -> q
                                               .scriptScore(scriptScoreQueryBuilder -> scriptScoreQueryBuilder
                                                       .script(script)
                                                       .query(QueryBuilders.bool().filter(QueryBuilders.term(builder-> builder.field("contentType").value(field))).filter(QueryBuilders.exists(builder -> builder.field("embedding"))).build()._toQuery())
                                               )
                                       )
                                       .withPageable(pageable)
                                       .build();
        return elasticsearchOperations.search(query, EDestination.class).stream()
                                      .map(SearchHit::getContent).toList();
    }

    private Script getScript(double[] userVector){
        JsonData jsonData;
        try{
           jsonData = JsonData.fromJson(new JsonMapper().writeValueAsString(userVector));

        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return Script.of(
                builder -> builder.inline(InlineScript.of(inline->
                        inline.lang(ScriptLanguage.Painless)
                              .source("cosineSimilarity(params.query_vector, 'embedding') + 1.0")
                              .params(Collections.singletonMap("query_vector", jsonData)))
                        )
        );
    }
}
