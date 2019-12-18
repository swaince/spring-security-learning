package com.github.swainc.conf;

import com.github.swainc.properties.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author swaince
 * @date 2019/12/18 9:13 下午
 */
@Configuration
public class ConfigurationBean {

    public ConfigurationBean(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    private SecurityProperties securityProperties;

    @Bean
    public BrowserProperties browserProperties() {
        return securityProperties.getBrowser();
    }

    @Bean
    public CodeProperties codeProperties() {
        return securityProperties.getCode();
    }

    @Bean
    public SmsCodeProperties smsCodeProperties() {
        return codeProperties().getSms();
    }

    @Bean
    public ImageCodeProperties imageCodeProperties() {
        return codeProperties().getImage();
    }

}
