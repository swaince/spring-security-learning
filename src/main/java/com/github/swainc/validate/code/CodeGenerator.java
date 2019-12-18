package com.github.swainc.validate.code;

/**
 * 验证码生成接口
 * @author swaince
 * @date 2019/12/18 8:56 下午
 */
public interface CodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    ValidateCode generate();
}
