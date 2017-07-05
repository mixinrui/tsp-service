package com.boxfish.tsp.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends BoxfishException {

    public final Integer returnCode = HttpStatus.BAD_REQUEST.value();

    private String returnMsg;

    public BusinessException(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public BusinessException() {
        returnMsg = "无效的请求,请重试";
    }
}
