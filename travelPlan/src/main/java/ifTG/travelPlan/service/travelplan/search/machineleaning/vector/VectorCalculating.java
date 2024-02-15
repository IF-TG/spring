package ifTG.travelPlan.service.travelplan.search.machineleaning.vector;

public final class VectorCalculating {
    public static double cosineSimilarity(double[] vectorA, double[] vectorB){
        if (vectorA.length!=vectorB.length) return 0.0;
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        int dimension = vectorA.length;
        for (int i = 0; i< dimension; i++){
            dotProduct += vectorA[i]*vectorB[i];
            normA += vectorA[i]*vectorA[i];
            normB += vectorB[i]*vectorB[i];
        }
        return dotProduct/(Math.sqrt(normA)*Math.sqrt(normB));
    }
}
