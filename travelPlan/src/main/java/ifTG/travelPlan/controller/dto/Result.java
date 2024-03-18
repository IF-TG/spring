package ifTG.travelPlan.controller.dto;

import ifTG.travelPlan.exception.StatusCode;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result<T> {
    T result;
    private String status;
    private String statusCode;
    private String message;

    public static <T> ResponseEntity<Result<T>> isSuccess(T value){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Result.<T>builder()
                        .result(value)
                        .status(StatusCode.OK.getHttpStatus().name())
                        .statusCode(StatusCode.OK.getCode())
                        .message(StatusCode.OK.getMessage())
                        .build()
                );
    }
    public static ResponseEntity<Object> isError(StatusCode statusCode){
        return ResponseEntity
                .status(statusCode.getHttpStatus())
                .body(Result.builder()
                        .result(null)
                        .status(statusCode.getHttpStatus().name())
                        .statusCode(statusCode.getCode())
                        .message(statusCode.getMessage())
                        .build()
                );
    }
    @Builder
    private Result(T result, String status, String statusCode, String message) {
        this.result = result;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }
}
