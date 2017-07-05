package com.boxfish.tsp.permission;

import lombok.Data;

@Data
public class UserInfo {

    private Long id;

    private String username;//账号

    private String realName;//用户真实姓名

    private String createTime;//账号创建时间

    private String updateTime;//账号更新

    private String loginTime;//登录时间

    private String lockStatus;//状态

    private String accessToken;//token

    private String invalidTime;//token失效时间

    public static UserInfo createUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(123456L);
        userInfo.setUsername("test@boxfish.cn");
        return userInfo;
    }
}
