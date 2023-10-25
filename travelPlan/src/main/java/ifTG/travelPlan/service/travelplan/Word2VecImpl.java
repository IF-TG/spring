package ifTG.travelPlan.service.travelplan;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Word2VecImpl implements Word2Vec{
    private static Word2VecImpl word2Vec;
    private final double [][] inputHiddenWeight = new double[100][100];
    private final double [][] hiddenOutputWeight = new double[100][100];
    private final HashMap<String, Integer> wordIdxMap = new HashMap<>();

    public static Word2VecImpl getInstance(){
        if (word2Vec == null){
            word2Vec = new Word2VecImpl();
        }
        return word2Vec;
    }

    private Word2VecImpl(){

    }

    public double getScore(String s1, String s2){
        return 0;
    }
}
