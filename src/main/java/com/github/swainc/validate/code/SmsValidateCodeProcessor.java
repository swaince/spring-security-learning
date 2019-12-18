package com.github.swainc.validate.code;

import com.github.swainc.properties.SmsCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author swaince
 * @date 2019/12/18 10:00 下午
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private SmsCodeProperties smsCodeProperties;

    @Override
    protected void process(ServletWebRequest webRequest, ValidateCode validateCode) throws Exception {
        String mobile = webRequest.getParameter(smsCodeProperties.getRequestMobileParam());
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
