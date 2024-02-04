package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
<<<<<<< HEAD
import ifTG.travelPlan.service.travelplan.search.machineleaning.DestinationOverViewVector;
=======
>>>>>>> d44af60ff76f20667a6a68c636650c12d1b637a9
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
