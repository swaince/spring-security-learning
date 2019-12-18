package com.github.swainc.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * 图形验证码
 * @author swaince
 * @date 2019/12/18 8:59 下午
 */
@Data
public class ImageValidateCode extends ValidateCode {

    public ImageValidateCode(String code, LocalDateTime expireIn, BufferedImage bufferedImage) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public ImageValidateCode(String code, long afterSeconds, BufferedImage bufferedImage) {
        super(code, afterSeconds);
        this.bufferedImage = bufferedImage;
    }

    /**
     *  图形验证码
     */
    private BufferedImage bufferedImage;

    public boolean isExpired() {
        return LocalDateTime.now(Clock.systemUTC()).isAfter(getExpireIn());
    }
}
