package ifTG.travelPlan.service.destination.morpheme;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DestinationOverviewNounExtractor {

    @Transactional(readOnly = true)
    List<List<String>> findAllNounGroupByDestination();

}
