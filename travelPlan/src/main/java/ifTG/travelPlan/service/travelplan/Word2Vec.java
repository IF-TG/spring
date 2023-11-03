package ifTG.travelPlan.service.travelplan;

public interface Word2Vec {
    void initData();
    double getScore(String s1, String s2);

}
