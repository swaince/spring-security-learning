package com.github.swainc.controller;

import com.github.swainc.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 验证码控制器
 * @author swaince
 * @date 2019/12/17 5:35 下午
 */
@RestController
public class CodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    private static final String VALIDATE_CODE_PROCESSOR_SUBFIX = "ValidateCodeProcessor";

    @GetMapping("/code/{type}")
    public void imageCodeGenerate(HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable String type) throws Exception {
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(type + VALIDATE_CODE_PROCESSOR_SUBFIX);
        validateCodeProcessor.create(new ServletWebRequest(request, response));
    }


}
