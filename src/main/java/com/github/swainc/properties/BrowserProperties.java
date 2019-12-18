package com.github.swainc.properties;

import com.github.swainc.constant.LoginType;
import lombok.Data;

/**
 * @author swaince
 * @date 2019/12/15 4:29 下午
 */
@Data
public class BrowserProperties {

    /**
     * web登陆页
     */
    private String loginPage = "/login.html";

    /**
     * 登陆成功后返回数据类型
     */
    private LoginType loginType = LoginType.JSON;
}
