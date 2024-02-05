package ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface Morpheme {
    void init();


    List<String> getNounByString(String s);

    Integer getIdx(String s);

    String getWord(Integer i);

    Map<String, Integer> getWordIdxMap();

    Map<Integer, String> getWordMap();
}
