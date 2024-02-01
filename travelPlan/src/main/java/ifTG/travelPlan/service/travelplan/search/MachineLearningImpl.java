package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MachineLearningImpl implements MachineLeaning {
    private final Morpheme morpheme;
    private final DestinationOverViewVector destinationOverViewVector;
    @Override
    public void init() {
        System.out.println("MachineLearningImpl.init");
        morpheme.init();
        destinationOverViewVector.initData();
    }
}
