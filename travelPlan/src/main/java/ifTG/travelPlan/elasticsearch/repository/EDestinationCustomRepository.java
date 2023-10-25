package ifTG.travelPlan.elasticsearch.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class EDestinationCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    public List<EDestination> findAllByUserKeywordAndGPTKeywordList(String userKeyword, List<String> gptKeywordList, Pageable pageable){

        Query multiMatchQuery = NativeQuery.builder()
                .withQuery(
                        q-> q.multiMatch(MultiMatchQuery.of(builder->
                                builder
                                        .query(userKeyword)
                                        .fields("title^2", "info", "blind_info")
                                        .boost(1.5f)
                                        .operator(Operator.Or)
                                        .type(TextQueryType.MostFields)
                        ))
                ).getQuery();

        List<Query> keywordMatchQueryList = new ArrayList<>();
        gptKeywordList.forEach(
                keyword->
                        keywordMatchQueryList.add(NativeQuery.builder().withQuery(
                                q-> q.match(builder->
                                        builder
                                                .field("keywordList")
                                                .query(keyword)
                                                .operator(Operator.Or)
                                )
                                ).getQuery()
                        )
        );

        /*Query mustBooleanQuery = QueryBuilders.bool().should(mustQuery).build()._toQuery();
        Query query = QueryBuilders.bool().must(mustBooleanQuery).should(shouldQueryList).build()._toQuery();*/

        Query query = QueryBuilders.bool().should(multiMatchQuery).should(keywordMatchQueryList).build()._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
                        .withQuery(query)
                        .withPageable(pageable)
                .build();
//page 추가하기
        log.info("{}", nativeQuery.getQuery().toString());

        SearchHits<EDestination> response =  elasticsearchOperations.search(nativeQuery, EDestination.class);

        return response.getSearchHits().stream().map(
                SearchHit::getContent
        ).collect(Collectors.toList());
    }

}
