package ifTG.travelPlan.service.destination;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserKeywordRedisCacheImpl implements UserKeywordRedisCache{
    private final RedisTemplate<String, List<String>> redisTemplate;
    private static final String USER_KEYWORD = "k_";

    @Override
    public List<String> getWordList(String keyword){
        List<String> wordList = redisTemplate.opsForValue().get(getKey(keyword));
        if (wordList!=null){
            redisTemplate.expire(getKey(keyword), 10, TimeUnit.HOURS);
        }
        return wordList;
    }

    @Override
    public void addWordList(String keyword, List<String> wordList){
        redisTemplate.opsForValue().set(getKey(keyword), wordList, 10, TimeUnit.HOURS);
    }
    private String getKey(String keyword) {
        return USER_KEYWORD + keyword;
    }
}
