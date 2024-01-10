package ifTG.travelPlan.controller.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {
    OK(HttpStatus.OK, "0000","SUCCESS"),
    INVALID_CONTENT_TYPE_ID(HttpStatus.BAD_REQUEST,"2001", "알 수 없는 컨텐츠 타입"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"6000", "알 수 없는 사용자")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    StatusCode(HttpStatus httpStatus,String code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}