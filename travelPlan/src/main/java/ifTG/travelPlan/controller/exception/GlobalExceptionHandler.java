package ifTG.travelPlan.controller.exception;

import ifTG.travelPlan.controller.dto.Result;
import ifTG.travelPlan.exception.CustomErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value= CustomErrorException.class)
    public ResponseEntity<Object> exceptionStatusCode(CustomErrorException e){
        return Result.isError(e.getStatusCode());
    }
}
