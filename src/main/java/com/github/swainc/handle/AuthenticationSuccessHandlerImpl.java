package com.github.swainc.handle;

import com.alibaba.fastjson.JSON;
import com.github.swainc.constant.LoginType;
import com.github.swainc.properties.BrowserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author swaince
 * @date 2019/12/16 9:45 下午
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private BrowserProperties browserProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("登陆成功");
        if (LoginType.REDIRECT.equals(browserProperties.getLoginType())) {
            log.info("重定向至目标页面");
            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            log.info("返回JSON数据");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(authentication));
        }
    }
}
