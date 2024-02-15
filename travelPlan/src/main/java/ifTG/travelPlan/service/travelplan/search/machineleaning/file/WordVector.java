package ifTG.travelPlan.service.travelplan.search.machineleaning.file;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class WordVector{
    double[][] inputHiddenWeight;
    double[][] hiddenOutputWeight;
    int dimension;
    Map<String, Integer> mapping;
}
