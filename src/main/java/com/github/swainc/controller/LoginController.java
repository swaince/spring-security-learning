package com.github.swainc.controller;

import com.github.swainc.properties.BrowserProperties;
import com.github.swainc.result.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author swaince
 * @date 2019/12/16 9:00 下午
 */
@Slf4j
@RestController
public class LoginController {

    public static final String HTML_SUBFIX = ".html";
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private BrowserProperties browserProperties;

    @RequestMapping("${spring.security.custom.loginPageProcess:/login/require}")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse loginPageProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (Objects.nonNull(savedRequest)) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("请求目标地址[ {} ]", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, HTML_SUBFIX)) {
                log.warn("当前用户尚未登陆，请求[ {} ]将重定向至登陆页[ {} ]", redirectUrl, browserProperties.getLoginPage());
                redirectStrategy.sendRedirect(request, response, browserProperties.getLoginPage());
            }
        }
        return new SimpleResponse("当前用户尚未登陆，请引导用户登陆后在进行访问");
    }


}
