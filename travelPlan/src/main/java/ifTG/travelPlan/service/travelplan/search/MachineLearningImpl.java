package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.Word2Vec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MachineLearningImpl implements MachineLeaning {
    private final Morpheme morpheme;
    private final Word2Vec word2Vec;
    @Override
    public void init() {
        System.out.println("MachineLearningImpl.init");
        morpheme.init();
        word2Vec.initData();
    }
}
