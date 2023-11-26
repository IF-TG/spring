package ifTG.travelPlan.exception.exceptionenum;

import lombok.Getter;

@Getter
public enum UserErrorCode {
    USER_NOT_FOUND("6000", "알 수 없는 사용자");

    private final String errorCode;
    private final String message;
    UserErrorCode(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

}
