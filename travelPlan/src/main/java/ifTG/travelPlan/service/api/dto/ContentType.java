package ifTG.travelPlan.service.api.dto;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ContentType {
    Sightseeing(12),
    Cultural_Facility(14),
    Event_Performance_Festival(15),
    Travel_Course(25),
    LeisureSports(28),
    Accommodation(32),
    Shopping(38),
    Restaurant(39);

    private final int value;

    ContentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Optional<ContentType> getContentType(int contentId){
        for (ContentType c: ContentType.values()){
            if (c.value == contentId){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    public static Optional<ContentType> getContentType(String contentId){
        for (ContentType c: ContentType.values()){
            if (c.value == Integer.parseInt(contentId)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    private static final Map<Integer, ContentType> lookup = new HashMap<>();

    static{
        for (ContentType c: values()){
            lookup.put(c.getValue(), c);
        }
    }

    public static ContentType of(int value){
        return lookup.get(value);
    }
}
