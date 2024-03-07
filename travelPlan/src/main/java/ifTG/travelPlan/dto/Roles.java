package ifTG.travelPlan.dto;

import lombok.Getter;

public enum Roles {
    OAUTH2_USER("OAUTH2_USER"),
    ANONYMOUS("ANONYMOUS");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
