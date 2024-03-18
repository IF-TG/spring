package ifTG.travelPlan.exception;

import lombok.Getter;

@Getter
public class CustomErrorException extends RuntimeException{
    StatusCode statusCode;
    public CustomErrorException(StatusCode statusCode){
        this.statusCode =statusCode;
    }
}
