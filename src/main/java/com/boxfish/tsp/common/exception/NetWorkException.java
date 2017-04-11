package com.boxfish.tsp.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class NetWorkException extends BoxfishException {

    public final Integer returnCode = 404;

    private String returnMsg;

    public NetWorkException(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public NetWorkException() {
        returnMsg = "网络抖了一下,请稍后再试";
    }
}
