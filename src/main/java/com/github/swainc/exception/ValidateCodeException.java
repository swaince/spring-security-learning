package com.github.swainc.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author swaince
 * @date 2019/12/18 10:45 下午
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
