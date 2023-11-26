package ifTG.travelPlan.elasticsearch.repository;

import co.elastic.clients.elasticsearch._types.InlineScript;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.ScriptLanguage;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.json.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class EDestinationCustomRepositoryTest {
    @Test
    void destinationQuery(){
        //given
        Script script = Script.of(
                builder->builder.inline(InlineScript.of(inline->
                        inline.lang(ScriptLanguage.Painless)
                              .source("cosineSimilarity(params.query_vector, doc['title_vector']) + 1.0")
                              .params(Collections.singletonMap("query_vector", JsonData.of(new double[]{1,2,3})))
                ))
        );

        NativeQuery nativeQuery = NativeQuery.builder()
                                             .withQuery(q -> q
                                                     .scriptScore(scriptScoreQueryBuilder -> scriptScoreQueryBuilder
                                                             .script(script)
                                                             .query(QueryBuilders.matchQuery("test", "test", Operator.Or, 1.0f)._toQuery())
                                                     )
                                             )
                                             .build();

        System.out.println("nativeQuery.getQuery().toString() = " + nativeQuery.getQuery().toString());
        //when

        //then
    }
}