package ifTG.travelPlan.exception;

import ifTG.travelPlan.dto.StatusCodeMessageDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 0xxx : 성공
 * 2xxx : 클라이언트와 서버간의 정보 차이로 인한 문제
 * 3xxx : 클라이언트 문제
 * 4xxx : 인증 관련
 * 5xxx : 서버 장애
 */
@Getter
@ToString
public enum StatusCode {
    OK(HttpStatus.OK, "0000","SUCCESS"),

    INVALID_CONTENT_TYPE_ID(HttpStatus.BAD_REQUEST,"2001", "알 수 없는 컨텐츠 타입"),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "2011", "삭제되었거나 알 수 없는 댓글입니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "2012", "삭제되었거나 알 수 없는 글입니다."),
    IS_ALREADY_DELETE(HttpStatus.NOT_FOUND, "2013", "이미 삭제된 댓글입니다."),
    NOT_FOUND_CONTENT(HttpStatus.NOT_FOUND, "2014", "알 수 없는 컨텐츠입니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"4000", "알 수 없는 사용자"),
    EXPIRED_LOGIN(HttpStatus.UNAUTHORIZED, "4001", "로그인이 만료되었습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "4002", "로그인 유지 시간 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "4003", "유효하지 않는 접근입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "4005", "중복된 닉네임입니다."),
    AUTHENTICATION_FAILED(HttpStatus.FORBIDDEN, "4444", "잘못된 접근입니다."),
    AUTHORITY_FAILED(HttpStatus.FORBIDDEN, "4445", "권한이 없습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "4008", "유효하지 않은 날짜 형식입니다."),
    INVALID_IMAGE_TYPE(HttpStatus.BAD_REQUEST, "4006", "지원하지 않는 포멧입니다."),

    Internal_Server_Error(HttpStatus.INTERNAL_SERVER_ERROR, "5001", "죄송합니다. 요청을 처리하는 과정에서 예상치 못한 서버 오류가 발생했습니다. 문제를 빠르게 해결하기 위해 노력하고 있습니다. 문제가 지속될 경우, 이메일로 문의해 주세요."),
    UNSUPPORTED_OAUTH2_REQUIREMENT(HttpStatus.NOT_IMPLEMENTED, "5002", "로그인 시도 중 문제가 발생하였습니다."),
    CANNOT_CREATE_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "5003", "이미지 저장에 실패했습니다.");





    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    StatusCode(HttpStatus httpStatus,String code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static StatusCode getStateCode(StatusCodeMessageDto dto){
        String code = dto.getCode();
        for (StatusCode statusCode : StatusCode.values()){
            if (statusCode.getCode().equals(code)){
                return statusCode;
            }
        }
        return null;
    }
}