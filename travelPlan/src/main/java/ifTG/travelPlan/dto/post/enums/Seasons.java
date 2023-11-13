package ifTG.travelPlan.dto.post.enums;

public enum Seasons {
    SPRING("봄"),
    SUMMER("여름"),
    AUTUMN("가을"),
    WINTER("겨울");

    private final String value;

    Seasons(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static String getInstance(String seasons){
        System.out.println("seasons = " + seasons);
        for (Seasons s : Seasons.values()){
            if (s.toString().equals(seasons)){
                return s.getValue();
            }
        }
        return null;
    }
}
