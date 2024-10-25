package net.kegui.api.system.feign;

import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.api.system.domain.SysOperationLog;
import net.kegui.api.system.feign.factory.RemoteLogFallbackFactory;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.core.constants.ServiceNameConstants;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 日志服务
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE,
		fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {

	/**
	 * 保存系统日志
	 * @param sysOperationLog 日志实体
	 * @return 结果
	 */
	@PostMapping(value = "/api/operationLog", headers = SecurityConstants.HEADER_FROM_IN)
	R<Boolean> saveLog(@RequestBody SysOperationLog sysOperationLog);

	/**
	 * 保存登录记录
	 * @param sysLoginInfo 登录结果
	 * @return 结果
	 */
	@PostMapping(value = "/api/loginInfo", headers = SecurityConstants.HEADER_FROM_IN)
	R<Boolean> saveLoginInfo(@RequestBody SysLoginInfo sysLoginInfo);

}
