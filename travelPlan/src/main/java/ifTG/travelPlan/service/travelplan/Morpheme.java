package ifTG.travelPlan.service.travelplan;

import java.util.List;

public interface Morpheme {
    List<String> findAllNounByDestination();


    List<String> getNounByString(String s);
}
