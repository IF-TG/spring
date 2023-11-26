package ifTG.travelPlan.service.travelplan.search;

import java.util.List;
import java.util.Map;

public interface Morpheme {
    void settingWordMap();

    List<String> findAllNounByDestination();

    List<String> getNounByString(String s);

    Integer getIdx(String s);

    String getWord(Integer i);

    Map<String, Integer> getWordIdxMap();

    Map<Integer, String> getWordMap();
}
