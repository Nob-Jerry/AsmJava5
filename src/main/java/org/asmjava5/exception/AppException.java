package org.asmjava5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.asmjava5.enums.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException{
    private ErrorCode errorCode;
}
