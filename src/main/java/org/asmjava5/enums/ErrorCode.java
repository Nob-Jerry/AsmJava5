package org.asmjava5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_SQL(500, "Bad SQL! Vui lòng check lại câu lệnh sql"),
    UNCAUGHT_EXCEPTION(500, "Uncaught Exception! Chưa bắt được ngoại lệ"),
    LIST_CLASS_EMPTY(1000, "List Class is empty"),
    CLASS_EMPTY(1000, "Class is empty"),
    USER_EMPTY(1000, "User is empty"),
    PASSWORD_INCORRECT(1001, "Password is incorrect"),
    CANNOT_SIGN_JWT(1001, "Cannot sign JWT"),
    INVALID_TOKEN(1001, "Invalid Token"),
    INVALID_DATE_TOKEN(1001, "Token đã hết hạn"),
    INVALID_SIGN(1001, "Chữ ký token không hợp lệ"),
    USER_NOT_EXIST(1002, "User Not Exist")
    ;

    private final Integer status;
    private final String message;
}
