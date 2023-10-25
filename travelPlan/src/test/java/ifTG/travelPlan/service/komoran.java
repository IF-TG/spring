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
        String s = "부산문화재단에서 운영하고 있는 감만창의문화촌은 문화예술을 통해 지역의 문화적 재생을 꿈꾸고 시민과 예술가가 만나 함께 건강한 문화생태계를 만드는 복합문화공간으로써의 역할을 수행하고자 설립되었다.\n" +
                "감만창의문화촌은 폐교를 리모델링하여 개관한 곳으로 세종대왕동상, 운동장의 놀이시설, 교실복도 등 어릴 때 추억이 곳곳에 묻어나 있다.\n" +
                "운영사업으로는 예술적 상상력을 시민들과 공유하고 문화예술을 향유할 수 있는 공간활용 프로그램인 [감만상상페스티벌], [감만상상데이] 등 기획형 프로그램을 운영하고 있다. 또한, 문화예술 프로그램으로는 [안녕하세요 예술씨], [감만상상갤러리] 등과, 창작지원 프로그램인 [입주예술가 창작공간 운영 및 지원], [커뮤니티 프로그램]을 운영하고 있다.\n" +
                "부산문화재단에서는 감만창의문화촌 외에도 홍티아트센터, F1963, 원도심 창작공간을 운영하고 있다.";
        KomoranResult komoranResult = komoran.analyze(s);
        log.info("result = {}", komoranResult.getPlainText());
        List<Token> tokenList = komoranResult.getTokenList();
        tokenList.stream().filter(t->t.getPos().equals(SYMBOL.NNP)||t.getPos().equals(SYMBOL.NNG)||t.getPos().equals(SYMBOL.NA)).forEach(System.out::println);

    }
}
