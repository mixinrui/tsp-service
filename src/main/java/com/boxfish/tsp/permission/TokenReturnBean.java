package com.boxfish.tsp.permission;

import lombok.Data;

import java.util.Map;

@Data
public class TokenReturnBean {

    private Integer code;

    private String message;

    private Map<String, Object> data;
}
