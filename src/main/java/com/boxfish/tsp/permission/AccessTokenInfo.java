package com.boxfish.tsp.permission;

import lombok.Data;

@Data
public class AccessTokenInfo {

    private Long tokenId;

    private String username;//账号

    private String accessToken;//token

    private String createTime;//账号创建时间

    private String updateTime;//账号更新

    private String invalidTime;//token失效时间

    public static AccessTokenInfo createAccessTokenInfo() {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setTokenId(123456L);
        accessTokenInfo.setUsername("test@boxfish.cn");

        return accessTokenInfo;
    }
}
