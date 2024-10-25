package net.kegui.framework.security.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 自定义手机登录处理
 */
public class SmsCodeException extends AuthenticationException {

	public SmsCodeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SmsCodeException(String msg) {
		super(msg);
	}

}
