package com.github.swainc.validate.code.sms;

import com.github.swainc.validate.code.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author swaince
 * @date 2019/12/18 10:10 下午
 */
@Slf4j
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("向手机号[ {} ]发送短信验证码[ {} ]", mobile, code);
    }
}
