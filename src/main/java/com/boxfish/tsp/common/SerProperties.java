package com.boxfish.tsp.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ser.conf")
@Data
public class SerProperties {
    private String orderApiUrl;
    private String fishcardApiUrl;
    private String userPhonenumUrl;
    private String userIdUrl;
    private String userInfoUrl;
    private String systemFeedbacktagUrl;
    private String fishcardBackfeeUrl;
    private String internetPingUrl;
    private String studentAreaInfoUrl;
}
