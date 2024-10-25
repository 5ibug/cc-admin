package net.kegui.framework.security.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: Feign 配置注册
 */
public class FeignConfig {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return new FeignRequestInterceptor();
	}

}
