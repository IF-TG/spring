package ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary;

import java.util.List;
import java.util.Map;

public interface Morpheme {
    void init();

    void init(Map<String,Integer> morpheme);

    List<String> getNounByString(String s);

    Integer getIdx(String s);

    String getWord(Integer i);

    Map<String, Integer> getWordIdxMap();

    Map<Integer, String> getWordMap();

    boolean isValid(Map<String, Integer> mapping);
}
