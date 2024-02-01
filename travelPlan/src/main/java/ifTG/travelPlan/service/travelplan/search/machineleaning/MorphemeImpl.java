package ifTG.travelPlan.service.travelplan.search.machineleaning;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MorphemeImpl implements Morpheme {
    protected final DestinationRepository destinationRepository;
    private final Map<String, Integer> wordIdxMap = new HashMap<>();
    private final Map<Integer, String> wordMap = new HashMap<>();

    @Override
    public void init(){
        List<String> nounByString = findAllNounByDestination();
        int count = 0;
        for (String s : nounByString) {
            if (!wordIdxMap.containsKey(s)){
                wordIdxMap.put(s, count);
                wordMap.put(count, s);
                count++;
            }
        }
    }

    private List<String> findAllNounByDestination(){
        List<Destination> destinationList = destinationRepository.findAll();
        String allDestinationOverViewByOverView = destinationList.stream().map(Destination::getOverview).toList().toString();
        return getNounByString(allDestinationOverViewByOverView);
    }

    @Override
    public List<String> getNounByString(String s) {
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult komoranResult = komoran.analyze(s);
        List<Token> tokenList = komoranResult.getTokenList();
        return tokenList.stream().filter(t -> t.getPos().equals(SYMBOL.NNP) || t.getPos().equals(SYMBOL.NNG))
                        .map(Token::getMorph).toList();
    }

    @Override
    public Integer getIdx(String s) {
        return wordIdxMap.get(s);
    }

    @Override
    public String getWord(Integer i) {
        return wordMap.get(i);
    }

    @Override
    public Map<String, Integer> getWordIdxMap() {
        return wordIdxMap;
    }

    @Override
    public Map<Integer, String> getWordMap() {
        return wordMap;
    }
}

