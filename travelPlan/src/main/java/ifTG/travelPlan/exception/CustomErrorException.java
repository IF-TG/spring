package ifTG.travelPlan.exception;

import ifTG.travelPlan.controller.dto.StatusCode;
import jdk.jshell.spi.ExecutionControl;
import lombok.Getter;

@Getter
public class CustomErrorException extends RuntimeException{
    StatusCode statusCode;
    public CustomErrorException(StatusCode statusCode){
        this.statusCode =statusCode;
    }
}
