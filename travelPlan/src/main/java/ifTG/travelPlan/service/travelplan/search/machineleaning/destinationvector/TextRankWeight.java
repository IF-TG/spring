package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

public interface TextRankWeight {
    double getScore(String s1, String s2);
    double getScore(double[] s1, double[] s2);
}
