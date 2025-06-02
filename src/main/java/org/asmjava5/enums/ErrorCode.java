package org.asmjava5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_SQL(500, "Bad SQL! Vui lòng check lại câu lệnh sql"),
    FAIL_TO_SAVE_UPDATE(501, "Fail to save or update"),
    UNCAUGHT_EXCEPTION(500, "Uncaught Exception! Chưa bắt được ngoại lệ"),
    FAIL_GET_LIST(1000, "Fail to get List"),
    FAIL_GET_ONE(1001, "Fail to get One"),
    FAIL_DELETE(1002, "Fail to delete"),
    ALREADY_EXIST(4002, "Already Exist"),
    T_EMPTY(1000, "T is not exist"),
    USER_EMPTY(1000, "User is not exist"),
    LIST_EMPTY(1000, "List is empty"),
    PASSWORD_INCORRECT(1001, "Password is incorrect"),
    CANNOT_SIGN_JWT(1001, "Cannot sign JWT"),
    INVALID_TOKEN(1001, "Invalid Token"),
    USER_NOT_EXIST(1002, "User Not Exist"),
    ;

    private final Integer status;
    private final String message;
}
