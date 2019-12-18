package com.github.swainc.validate.code;

import com.github.swainc.properties.ImageCodeProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

/**
 * @author swaince
 * @date 2019/12/18 9:20 下午
 */
@Component
@Slf4j
public class ImageCodeGenerator implements CodeGenerator {

    public ImageCodeGenerator(ImageCodeProperties imageCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
    }

    private ImageCodeProperties imageCodeProperties;

    @Override
    public ValidateCode generate() {
        VerifyCode verifyCode = new VerifyCode(imageCodeProperties.getWidth(),
                imageCodeProperties.getHeigth(),
                imageCodeProperties.getLength());
        BufferedImage image = verifyCode.getImage();

        log.info("生成图形验证码[ {} ]", verifyCode.getText());

        return new ImageValidateCode(verifyCode.getText(),
                imageCodeProperties.getExpireSeconds(), image);
    }
}
