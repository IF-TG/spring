package ifTG.travelPlan.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.assertj.core.api.Assertions;
=======
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
=======
import java.util.List;
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class komoran {
    @Test
    public void komoran(){
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
<<<<<<< HEAD
        String s = "나에게는 아름다운 물건 들어왔습니다요.";
        KomoranResult komoranResult = komoran.analyze(s);
        log.info("result = {}", komoranResult.getPlainText());
        List<Token> tokenList = komoranResult.getTokenList();
        tokenList.stream().filter(t->t.getPos().equals(SYMBOL.NNP)||t.getPos().equals(SYMBOL.NNG)).forEach(System.out::println);

    }

    @Test
    public void test(){
        List<String> list = Arrays.asList("1", "2", "3", "1");
        Map<String, Integer> map = IntStream.range(0, list.size())
                                            .boxed()
                                            .collect(Collectors.toMap(list::get, Function.identity(), (old, newv)->old));
        System.out.println(map);
    }
=======
        String s = "좋은 물건 들어왔습니다요.";
        KomoranResult komoranResult = komoran.analyze(s);
        log.info("result = {}", komoranResult.getPlainText());
        List<Token> tokenList = komoranResult.getTokenList();
        tokenList.stream().filter(t->t.getPos().equals(SYMBOL.NNP)||t.getPos().equals(SYMBOL.NNG)||t.getPos().equals(SYMBOL.NA)).forEach(System.out::println);

    }
>>>>>>> 0459481086cd14d65c9d0552a61060c7de1850de
}
