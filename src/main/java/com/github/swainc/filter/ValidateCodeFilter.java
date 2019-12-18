package com.github.swainc.filter;

import com.github.swainc.exception.ValidateCodeException;
import com.github.swainc.properties.SecurityProperties;
import com.github.swainc.validate.code.ImageValidateCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 验证码校验过滤器
 * @author swaince
 * @date 2019/12/18 10:36 下午
 */
@Data
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(StringUtils.equals(securityProperties.getLoginProcessingUrl(), request.getRequestURI()) &&
                StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                log.info("进入验证码校验过滤器");
                validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest webRequest) throws ValidateCodeException, ServletRequestBindingException {

        ImageValidateCode codeImage = (ImageValidateCode)sessionStrategy.getAttribute(webRequest, "CODE_IMAGE");
        String code = ServletRequestUtils.getStringParameter(webRequest.getRequest(),
                securityProperties.getCode().getImage().getRequestSmsCodeParam());
        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (Objects.isNull(codeImage)) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeImage.isExpired()) {
            sessionStrategy.removeAttribute(webRequest, "CODE_IMAGE");
            throw new ValidateCodeException("验证码已失效");
        }
        if(!StringUtils.equalsIgnoreCase(codeImage.getCode(), code)) {
            throw new ValidateCodeException("验证码不正确");
        }
        log.info("验证码校验成功");
        sessionStrategy.removeAttribute(webRequest, "CODE_IMAGE");
    }
}
