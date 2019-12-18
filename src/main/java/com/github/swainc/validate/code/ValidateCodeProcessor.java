package com.github.swainc.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author swaince
 * @date 2019/12/18 9:39 下午
 */
public interface ValidateCodeProcessor {

    /**
     *  生成验证码
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;
}
