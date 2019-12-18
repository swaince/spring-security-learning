package com.github.swainc.validate.code;

import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * 验证码
 * @author swaince
 * @date 2019/12/18 8:56 下午
 */
@Data
public class ValidateCode {

    public ValidateCode(String code, LocalDateTime expireIn) {
        this.code = code;
        this.expireIn = expireIn;
    }

    public ValidateCode(String code, long afterSeconds) {
        this.code = code;
        this.expireIn = LocalDateTime.now(Clock.systemUTC()).plusSeconds(afterSeconds);
    }

    /**
     * 验证码
     */
    private String code;

    /**
     * 失效时间
     */
    private LocalDateTime expireIn;
}
