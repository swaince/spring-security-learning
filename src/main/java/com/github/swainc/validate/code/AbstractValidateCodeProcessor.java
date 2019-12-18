package com.github.swainc.validate.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author swaince
 * @date 2019/12/18 9:40 下午
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor{

    @Autowired
    private Map<String, CodeGenerator> codeGenerators;

    private static final String CODE_GENERATOR_SUBFIX = CodeGenerator.class.getSimpleName();

    private static final String SESSSION_KEY = "CODE_";

    protected SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest request) throws Exception{
        ValidateCode validateCode = generate(request);
        save(request, validateCode);
        process(request, validateCode);
    }

    /**
     * 处理验证码
     * @param webRequest
     * @param validateCode
     * @throws Exception
     */
    protected abstract void process(ServletWebRequest webRequest, ValidateCode validateCode) throws Exception;

    /**
     * 保存验证码
     * @param webRequest
     * @param validateCode
     */
    protected void save(ServletWebRequest webRequest, ValidateCode validateCode) {
        sessionStrategy.setAttribute(webRequest,
                SESSSION_KEY + StringUtils.upperCase(getValidateCodeType(webRequest)), validateCode);
    }

    protected ValidateCode generate(ServletWebRequest webRequest) {
        String codeType = getValidateCodeType(webRequest);
        CodeGenerator codeGenerator = codeGenerators.get(codeType + CODE_GENERATOR_SUBFIX);
        return codeGenerator.generate();
    }

    /**
     * 获取验证码的类型： 短信验证码 ｜ 图形验证码
     * @param webRequest
     * @return
     */
    private String getValidateCodeType(ServletWebRequest webRequest) {
        return StringUtils.substringAfterLast(webRequest.getRequest().getRequestURI(), "/");
    }
}
