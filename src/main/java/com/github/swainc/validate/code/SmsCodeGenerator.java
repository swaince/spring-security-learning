package com.github.swainc.validate.code;

import com.github.swainc.properties.SmsCodeProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author swaince
 * @date 2019/12/18 9:02 下午
 */
@Component
@Slf4j
public class SmsCodeGenerator implements CodeGenerator {

    public SmsCodeGenerator(SmsCodeProperties smsCodeProperties) {
        this.smsCodeProperties = smsCodeProperties;
    }

    private SmsCodeProperties smsCodeProperties;

    @Override
    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(smsCodeProperties.getLength());
        log.info("生成短信验证码[ {} ]", code);
        return new ValidateCode(code, smsCodeProperties.getExpireSeconds());
    }
}
