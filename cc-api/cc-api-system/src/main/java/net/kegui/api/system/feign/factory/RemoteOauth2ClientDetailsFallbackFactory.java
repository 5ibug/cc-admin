package net.kegui.api.system.feign.factory;

import net.kegui.api.system.domain.SysClientDetails;
import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.api.system.feign.RemoteLogService;
import net.kegui.api.system.domain.SysOperationLog;
import net.kegui.api.system.feign.RemoteOauth2ClientDetailsService;
import net.kegui.framework.core.application.domain.AjaxResult;
import net.kegui.framework.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: Oauth2服务降级处理
 */
@Component
public class RemoteOauth2ClientDetailsFallbackFactory implements FallbackFactory<RemoteOauth2ClientDetailsService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteOauth2ClientDetailsFallbackFactory.class);

	@Override
	public RemoteOauth2ClientDetailsService create(Throwable throwable) {
		log.error("Oauth2服务调用失败:{}", throwable.getMessage());
		return new RemoteOauth2ClientDetailsService() {

			@Override
			public R<SysClientDetails> getClientDetailsById(String clientId) {
				return R.fail();
			}
		};
	}

}
