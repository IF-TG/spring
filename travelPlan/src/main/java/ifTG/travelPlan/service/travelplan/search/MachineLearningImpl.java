package ifTG.travelPlan.service.travelplan.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MachineLearningImpl implements MachineLeaning {
    private final Morpheme morpheme;
    private final Word2Vec word2Vec;
    @Override
    public void init() {
        morpheme.init();
        word2Vec.initData();
    }
}
