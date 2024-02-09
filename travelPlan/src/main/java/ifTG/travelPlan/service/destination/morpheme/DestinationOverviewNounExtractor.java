package ifTG.travelPlan.service.destination.morpheme;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface DestinationOverviewNounExtractor {

    @Transactional(readOnly = true)
    List<List<String>> findAllNounGroupByDestination();

    @Transactional(readOnly = true)
    Map<Long, List<String>> findAllNounMappingByDestination();

    Integer getIdx(String s);
}
