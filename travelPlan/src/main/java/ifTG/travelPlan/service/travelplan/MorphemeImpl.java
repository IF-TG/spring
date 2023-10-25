package ifTG.travelPlan.service.travelplan;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.constant.SYMBOL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MorphemeImpl implements Morpheme {
    private final DestinationRepository destinationRepository;

    @Override
    public List<String> findAllNounAdjByDestination(){
        List<Destination> destinationList = destinationRepository.findAll();
        String allDestinationOverview = destinationList.stream().map(Destination::getOverview).toList().toString();
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult komoranResult = komoran.analyze(allDestinationOverview);
        List<Token> tokenList = komoranResult.getTokenList();
        //List<String> result = tokenList.stream().filter(t->t.getPos().equals(SYMBOL.NNP)||t.getPos().equals(SYMBOL.NNG)||t.getPos().equals(SYMBOL.)).forEachOrdered(t->);
        return null;
    }
}
