package ifTG.travelPlan.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class komoran {
    @Test
    public void komoran(){
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        String s = "좋은 물건 들어왔습니다요.";
        KomoranResult komoranResult = komoran.analyze(s);
        log.info("result = {}", komoranResult.getPlainText());
        List<Token> tokenList = komoranResult.getTokenList();
        tokenList.stream().filter(t->t.getPos().equals(SYMBOL.NNP)||t.getPos().equals(SYMBOL.NNG)||t.getPos().equals(SYMBOL.NA)).forEach(System.out::println);

    }
}
