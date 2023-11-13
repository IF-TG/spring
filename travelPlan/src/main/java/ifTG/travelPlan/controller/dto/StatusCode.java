package ifTG.travelPlan.controller.dto;

public enum StatusCode {
    ERROR(-1),
    OK(0);
    private final int value;
    StatusCode(int value) {
        this.value = value;
    }
}
