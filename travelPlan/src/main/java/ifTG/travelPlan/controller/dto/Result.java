package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Result<T> {
    T result;
    private StatusCode statusCode;
    private String message;
    public Result(T value) {
        this.result = value;
        statusCode = StatusCode.OK;
        message = "SUCCESS";
    }

    public Result<T> isError(String message){
        this.message = message;
        statusCode = StatusCode.ERROR;
        return this;
    }
}
