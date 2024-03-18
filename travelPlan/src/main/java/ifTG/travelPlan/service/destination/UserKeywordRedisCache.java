package ifTG.travelPlan.service.destination;

import java.util.List;

public interface UserKeywordRedisCache {
    List<String> getWordList(String keyword);

    void addWordList(String keyword, List<String> wordList);

}
