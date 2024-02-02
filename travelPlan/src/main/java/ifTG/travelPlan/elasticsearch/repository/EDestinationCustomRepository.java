package ifTG.travelPlan.elasticsearch.repository;

import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.indices.Queries;
import co.elastic.clients.json.JsonData;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ScriptType;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.reindex.ReindexRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class EDestinationCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    public List<EDestination> findAllByUserKeywordAndGPTKeywordList(String userKeyword, List<String> gptKeywordList, Pageable pageable){

        Query userQuery = getUserKeywordQuery(userKeyword);
        List<Query> keywordMatchQueryList = getGptKeywordQuery(gptKeywordList);
        Query query = QueryBuilders.bool().should(userQuery).should(keywordMatchQueryList).build()._toQuery();
        SearchHits<EDestination> response = getSearchHits(pageable, query);
        return response.getSearchHits().stream().map(
                SearchHit::getContent
        ).collect(Collectors.toList());
    }

    private static Query getUserKeywordQuery(String userKeyword) {
        return NativeQuery.builder()
                          .withQuery(
                                  q-> q.multiMatch(
                                          builder-> builder
                                                  .query(userKeyword)
                                                  .fields("title^2", "info", "blind_info", "address", "keywordList")
                                                  .boost(1.5f)
                                                  .operator(Operator.Or)
                                                  .type(TextQueryType.MostFields)
                                  )
                                           ).getQuery();
    }
    private static List<Query> getGptKeywordQuery(List<String> gptKeywordList) {
        return gptKeywordList.stream().map(
                keyword-> NativeQuery.builder().withQuery(
                                p->p.match(builder ->
                                        builder
                                                .field("keywordList")
                                                .query(keyword)
                                                .operator(Operator.Or)
                                )
                        ).getQuery()
        ).toList();
    }
    private SearchHits<EDestination> getSearchHits(Pageable pageable, Query query) {
        NativeQuery nativeQuery = NativeQuery.builder()
                        .withQuery(query)
                        .withPageable(pageable)
                .build();
        System.out.println("nativeQuery = " + nativeQuery.getQuery().toString());
        return elasticsearchOperations.search(nativeQuery, EDestination.class);
    }
}
