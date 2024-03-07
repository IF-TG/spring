package ifTG.travelPlan.service.travelplan.search.machineleaning.embedding;

public class NormalizedVector {
    public static void normalizedVector(double[] vector){
        double norm = 0;
        for (double v: vector){
            norm += v*v;
        }
        norm = Math.sqrt(norm);
        for (int i =0; i<vector.length; i++){
            vector[i] = vector[i]/norm;
        }
    }
}
