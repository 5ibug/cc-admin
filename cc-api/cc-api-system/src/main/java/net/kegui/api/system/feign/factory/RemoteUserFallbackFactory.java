package net.kegui.api.system.feign.factory;

import net.kegui.api.system.feign.RemoteUserService;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 用户信息服务降级处理
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

	@Override
	public RemoteUserService create(Throwable throwable) {
		log.error("用户服务调用失败:{}", throwable.getMessage());
		return (username) -> R.fail();
	}

}
