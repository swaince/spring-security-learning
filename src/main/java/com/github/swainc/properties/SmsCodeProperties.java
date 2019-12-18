package com.github.swainc.properties;

import lombok.Data;

/**
 * @author swaince
 * @date 2019/12/18 9:04 下午
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 验证码有效时常
     */
    private long expireSeconds = 60;

    /**
     * 手机号请求参数字段
     */
    private String requestMobileParam = "mobile";

    /**
     * 验证码请求参数字段
     */
    private String requestSmsCodeParam = "code";
}
