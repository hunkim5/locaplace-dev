package com.locaplace.api.common.response.apidoc.common;

public class DocCommonResponse {

    public static final String VALID_TOKEN = """
            {
              "status": 200,
              "data": null,
              "code": "SEND_SUCCESS",
              "message": "인증에 성공하였습니다."
            }""";

    public static final String INVALID_TOKEN = """
            {
              "status": 422,
              "data": null,
              "code": "USER_002",
              "message": "유효하지 않은 이메일 인증 토큰입니다."
            }""";

    public static final String SEND_EMAIL_SUCCESS = """
            {
              "status": 200,
              "data": null,
              "code": "SEND_SUCCESS",
              "message": "이메일 전송에 성공하였습니다."
            }""";
    public static final String INVALID_REQUEST = """
            {
              "status": 401,
              "data": null,
              "code": "INVALID_REQUEST",
              "message": "잘못된 요청입니다."
            }""";
}
