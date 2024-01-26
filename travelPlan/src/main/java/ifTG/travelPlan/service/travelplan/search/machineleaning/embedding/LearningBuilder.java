package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

import lombok.Builder;

@Builder
public class EmbeddingBuilder {
    private int epoch;
    private double[][] inputHiddenWeight;
    private double[][][] hiddenWeight;
    private double[][] hiddenOutputWeight;

    private Double learnRate;
    private Integer window;

}
