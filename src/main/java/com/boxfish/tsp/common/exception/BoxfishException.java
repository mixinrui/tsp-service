package com.boxfish.tsp.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode
public class BoxfishException extends RuntimeException {

    private final static HttpStatus status = HttpStatus.OK;

    private String returnMsg;

    protected BoxfishException(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public BoxfishException() {
    }

    public Integer getReturnCode() {
        return status.value();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

}
