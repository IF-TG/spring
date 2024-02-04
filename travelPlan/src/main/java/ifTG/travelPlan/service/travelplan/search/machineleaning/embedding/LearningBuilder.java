package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class LearningBuilder{
    private int epoch;
    private List<List<String>> documentWordList;
    private WeightBuilder weightBuilder;
    private int dimension;
    private Double learnRate;
    private Integer window;

    public void setWeightBuilder(WeightBuilder weightBuilder){
        this.weightBuilder = weightBuilder;
    }
}
