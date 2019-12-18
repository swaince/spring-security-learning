package com.github.swainc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author swaince
 * @date 2019/12/15 4:26 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = SecurityProperties.SECURITY_CONF_PROFIX)
public class SecurityProperties {

    public static final String SECURITY_CONF_PROFIX = "spring.cloud.security.custom";

    /**
     * 浏览器相关配置项
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 登陆请求地址
     */
    private String loginProcessingUrl = "/api/login";
    /**
     * 登陆页处理器
     */
    private String loginPageProcess = "/login/require";
}
