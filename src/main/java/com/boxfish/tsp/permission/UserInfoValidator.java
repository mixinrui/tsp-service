package com.boxfish.tsp.permission;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@CrossOrigin
@Component
public class UserInfoValidator {

    @Value("${token-validate-api}")
    private String TOKEN_VALIDATE_API;

    private Logger logger = LoggerFactory.getLogger(UserInfoValidator.class);

    @Autowired
    private RestTemplate restTemplate;

    public UserInfo checkToken(String token) {
        if (isEmpty(token)) {
            logger.info("@checkToken - 非法登陆");
            throw new RuntimeException("非法登陆,传入参数不能为空");
        }

        String url = TOKEN_VALIDATE_API + "/box/fish/access/token/query/self";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("BoxFishAccessToken", token);
        HttpEntity request = new HttpEntity(httpHeaders);
        TokenReturnBean tokenCheckObject;
        try {
            tokenCheckObject = restTemplate.postForObject(url, request, TokenReturnBean.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            tokenCheckObject = null;
        }
        if (tokenCheckObject != null && tokenCheckObject.getCode() == 0) {
            UserInfo userInfo = new UserInfo();
            AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
            Map<String, Object> obj = tokenCheckObject.getData();
            Map<String, Object> tokenObj = (Map<String, Object>) obj.get("accessTokenInfo");
            Map<String, Object> userObj = (Map) obj.get("userInfo");

            try {
                BeanUtilsBean2.getInstance().copyProperties(userInfo, userObj);
                BeanUtilsBean2.getInstance().copyProperties(accessTokenInfo, tokenObj);

                userInfo.setAccessToken(accessTokenInfo.getAccessToken());
                userInfo.setInvalidTime(accessTokenInfo.getInvalidTime());
            } catch (Exception e) {
                e.printStackTrace();
                userInfo = null;
            }

            return userInfo;
        } else {
            return null;
        }
    }
}
