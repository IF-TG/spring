package ifTG.travelPlan.service.travelplan;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Slf4j
public class TextRankImpl implements TextRank{

    //NNP, NNG, VA
    @Override
    public List<String> textRank(String text){
        List<String> keywordList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(text, ".");
        String s = null;

        while(st.hasMoreTokens()) {
            s = st.nextToken();
            Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
            KomoranResult komoranResult = komoran.analyze(s);
            List<Token> tokenList =  komoranResult.getTokenList();

        }
        return null;
    }

    static class Node{
        private String token;
        private final List<LinkedNode> linked = new ArrayList<>();
    }
    static class LinkedNode{
        private Integer weight;
        private Node linkedNode;

        public LinkedNode(Integer weight, Node linkedNode) {
            this.weight = weight;
            this.linkedNode = linkedNode;
        }
    }
}
