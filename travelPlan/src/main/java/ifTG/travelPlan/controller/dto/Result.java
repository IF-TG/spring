package ifTG.travelPlan.controller.dto;

import lombok.Getter;

@Getter
public class Result<T> {
    T result;
    public Result(T value) {
        this.result = value;
    }
}
