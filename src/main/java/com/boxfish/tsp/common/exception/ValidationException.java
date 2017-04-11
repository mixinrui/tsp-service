package com.boxfish.tsp.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ValidationException extends BoxfishException {

    public final Integer returnCode = 400;

    private String returnMsg;

    public ValidationException(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public ValidationException() {
        this.returnMsg = "参数错误,请检查!!";
    }
}
