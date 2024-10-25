package net.kegui.api.system.feign;

import net.kegui.api.system.feign.factory.RemoteUserFallbackFactory;
import net.kegui.api.system.model.UserInfo;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.core.constants.ServiceNameConstants;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 用户信息服务
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE,
		fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户信息
	 * @param username 用户名称
	 * @return R<UserInfo>
	 */
	@GetMapping(value = "/api/user/info/{username}", headers = SecurityConstants.HEADER_FROM_IN)
	R<UserInfo> getUserInfo(@PathVariable("username") String username);

}
