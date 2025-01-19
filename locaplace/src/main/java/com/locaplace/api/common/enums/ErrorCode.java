package com.locaplace.api.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //USER
    DUPLICATED_EMAIL(409, "USER_001", "이미 존재하는 이메일입니다."),
    INVALID_EMAIL_VERIFICATION_TOKEN(422, "USER_002", "유효하지 않은 이메일 인증 토큰입니다."),
    USER_NOT_FOUND(404, "USER_003", "이 이메일의 유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(401, "USER_004", "유효하지 않은 비밀번호입니다."),
    NEED_PASSWORD_CHANGE(401, "UER_004", "비밀번호를 변경 후 로그인할 수 있습니다."),
    VERIFICATION_INFO_NOT_FOUND(404, "USER_005", "해당하는 유저 인증 정보를 찾을 수 없습니다."),

    //COMMON
    INVALID_TOKEN(401, "COMMON_001", "잘못된 토큰 정보입니다."),
    UNAUTHORIZED_ACCESS(401, "COMMON_002", "인증이 완료되지 않았습니다."),
    INVALID_REQUEST(401, "COMMON_003", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "SERVER_ERROR", "알 수 없는 오류가 발생하였습니다."),

    //ENCRYPT, DECRYPT
    NO_SUCH_ALGORITHM(500, "ENCRYPT_001", "Raised NoSuchAlgorithmException"),
    NO_SUCH_PADDING(500, "ENCRYPT_002", "Raised NoSuchPaddingException"),
    INVALID_KEY(500, "ENCRYPT_003", "Raised InvalidKeyException"),
    INVALID_ALGORITHM_PARAMETER(500, "ENCRYPT_004", "Raised InvalidAlgorithmParameterException"),
    ILLEGAL_BLOCK_SIZE(500, "ENCRYPT_005", "Raised IllegalBlockSizeException"),
    BAD_PADDING(500, "ENCRYPT_006", "Raised BadPaddingException"),
    UNSUPPORTED_ENCODING(500, "ENCRYPT_007", "Raised UnsupportedEncodingException"),
    //EXTERNAL
    TOKEN_EXPIRED(401, "NICE_001", "토큰 사용기간이 만료되었습니다."),
    TOKEN_INVALID(401, "NICE_002", "유효하지 않은 토큰입니다."),
    TOKEN_EMPTY(401, "NICE_003", "토큰 정보가 존재하지 않습니다."),
    TOKEN_PERIOD_INVALID(401, "NICE_004", "토큰 유효기간이 올바르지 않습니다."),

    EXTERNAL_API_ERROR(500, "EXTERNAL_API_ERROR", "외부 API 연동 과정에서 오류가 발생하였습니다.");

    private final int statusCode;
    private final String code;
    private final String message;

}