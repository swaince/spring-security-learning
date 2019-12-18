package com.github.swainc.validate.code;

/**
 *  验证码发送器
 * @author swaince
 * @date 2019/12/18 10:09 下午
 */
public interface SmsCodeSender {

    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @param code 验证码
     */
     void send(String mobile, String code);
}
