package com.github.swainc.handle;

import com.alibaba.fastjson.JSON;
import com.github.swainc.properties.BrowserProperties;
import com.github.swainc.constant.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author swaince
 * @date 2019/12/16 9:56 下午
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private BrowserProperties browserProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登陆失败");
        if (LoginType.REDIRECT.equals(browserProperties.getLoginType())) {
            log.info("重定向至登陆页");
            super.onAuthenticationFailure(request, response, exception);
        } else {
            log.info("返回JSON数据");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(exception));
        }
    }
}
