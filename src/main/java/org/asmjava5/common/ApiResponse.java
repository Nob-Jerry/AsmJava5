package org.asmjava5.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ApiResponse<T> {
    private Integer status;
    private Boolean success;
    private T data;
    private String message;
}
