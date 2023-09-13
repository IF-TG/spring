package ifTG.travelPlan.service.api.dto;

public enum ContentType {
    Sightseeing(12),
    Cultural_Facility(14),
    Event_Performance_Festival(15),
    Travel_Course(25),
    Recreation(28),
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
}
