package net.kegui.api.system.feign;

import net.kegui.api.system.domain.SysClientDetails;
import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.api.system.domain.SysOperationLog;
import net.kegui.api.system.feign.factory.RemoteLogFallbackFactory;
import net.kegui.api.system.feign.factory.RemoteOauth2ClientDetailsFallbackFactory;
import net.kegui.framework.core.application.domain.AjaxResult;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.core.constants.ServiceNameConstants;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: Oauth2服务
 */
@FeignClient(contextId = "RemoteOauth2ClientDetailsService", value = ServiceNameConstants.SYSTEM_SERVICE,
		fallbackFactory = RemoteOauth2ClientDetailsFallbackFactory.class)
public interface RemoteOauth2ClientDetailsService {

	/**
	 * 获取终端配置详细信息
	 * @param clientId 终端ID
	 * @return JsonResult
	 */
	@GetMapping(value = "/api/client/{clientId}", headers = SecurityConstants.HEADER_FROM_IN)
	R<SysClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId);

}
