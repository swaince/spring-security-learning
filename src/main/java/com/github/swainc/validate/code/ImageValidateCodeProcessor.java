package com.github.swainc.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author swaince
 * @date 2019/12/18 10:00 下午
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Override
    protected void process(ServletWebRequest webRequest, ValidateCode validateCode) throws Exception {
        ImageValidateCode imageValidateCode = (ImageValidateCode) validateCode;
        ImageIO.write(imageValidateCode.getBufferedImage(), "PNG",
                webRequest.getResponse().getOutputStream());
    }
}
