package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import java.util.List;

public interface TextRank {
    //NNP, NNG, VA
    List<String> textRank(String text);
}
