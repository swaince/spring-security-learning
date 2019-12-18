package com.github.swainc.properties;

import lombok.Data;

/**
 * @author swaince
 * @date 2019/12/18 9:05 下午
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        super();
        //  设置图形验证码的长度为4
        setLength(4);
    }

    /**
     * 图片的长度
     */
    private int width = 67;

    /**
     * 图片的宽度
     */
    private int heigth = 23;
}
