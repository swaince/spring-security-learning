package com.github.swainc.properties;

import lombok.Data;

/**
 * @author swaince
 * @date 2019/12/18 9:03 下午
 */
@Data
public class CodeProperties {

    /**
     * 短信验证码配置类
     */
    SmsCodeProperties sms = new SmsCodeProperties();

    /**
     * 图形验证码配置类
     */
    ImageCodeProperties image = new ImageCodeProperties();
}
