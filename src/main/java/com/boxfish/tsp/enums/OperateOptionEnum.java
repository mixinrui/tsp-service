package com.boxfish.tsp.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by mk on 17/4/18.
 */
public enum OperateOptionEnum {

    PREVIOUS(-1),
    NEXT(1),
    UNKNOWN(0);

    private int code;

    OperateOptionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static OperateOptionEnum getVariable(String operateOption) {
        if (StringUtils.isBlank(operateOption)) {
            return UNKNOWN;
        }
        try{
            OperateOptionEnum operateOptionEnum = OperateOptionEnum.valueOf(operateOption.toUpperCase());
            return operateOptionEnum == null ? UNKNOWN : operateOptionEnum;
        }catch (Exception e){
            return UNKNOWN;
        }
    }

}
