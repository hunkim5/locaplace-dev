package com.locaplace.api.common.response.apidoc.user;

public class DocUserReponse {

    //Success
    public static final String LOGIN_SUCCESS = """
            {
              "status": 200,
              "data": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJiYWVrZW5vdWdoQGdtYWlsLmNvbSIsImlhdCI6MTczNTg5NTMxNiwiZXhwIjoxNzM1OTgxNzE2fQ.i1hSo2qyjXWyuuuoCI_JXtZWjy-PqeGS2DAvfQcOGSHosIyKANN8mv2C_j3g8yF8",
              "code": "PERMIT",
              "message": "로그인 성공"
            }""";

    public static final String EMAIL_VERIFICATION_SUCCESS = """
            {
              "status": 200,
              "data": null,
              "code": "USER_VERIFIED",
              "message": "이메일 인증이 완료되었습니다."
            }""";

    //Exception
    public static final String USER_NOT_FOUND = """
            {
              "status": 404,
              "data": null,
              "code": "USER_003",
              "message": "이 이메일의 유저를 찾을 수 없습니다."
            }""";

    public static final String INVALID_PASSWORD = """
            {
              "status": 401,
              "data": null,
              "code": "USER_004",
              "message": "유효하지 않은 비밀번호입니다."
            }""";

    public static final String SIGNUP_SUCCESS = """
            {
              "status": 201,
              "data": null,
              "code": "USER_CREATED",
              "message": "회원가입에 성공하였습니다."
            }""";

    public static final String DUPLICATED_EMAIL = """
            {
              "status": 409,
              "data": null,
              "code": "USER_001",
              "message": "이미 존재하는 이메일입니다."
            }""";

    public static final String PASSWORD_CHANGE_SUCCESS = """
            {
              "status": 200,
              "data": null,
              "code": "PASSWORD_CHANGE_SUCCESS",
              "message": "패스워드 변경에 성공하였습니다."
            }""";

    public static final String NICKNAME_CHANGE_SUCCESS = """
            {
              "status": 200,
              "data": null,
              "code": "PASSWORD_CHANGE_SUCCESS",
              "message": "닉네임 변경에 성공하였습니다."
            }""";
}
