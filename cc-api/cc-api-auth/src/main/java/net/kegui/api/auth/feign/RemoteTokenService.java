package net.kegui.api.auth.feign;

import net.kegui.api.auth.feign.domain.dto.TokenDTO;
import net.kegui.api.auth.feign.domain.vo.TokenVo;
import net.kegui.api.auth.feign.factory.RemoteTokenFallbackFactory;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.core.constants.ServiceNameConstants;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 令牌管理服务
 */
@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE,
		fallbackFactory = RemoteTokenFallbackFactory.class)
public interface RemoteTokenService {

	/**
	 * 分页查询token 信息
	 * @param tokenDTO TokenDTO
	 * @return R<TableDataInfo>
	 */
	@GetMapping(value = "/api/token/pageQuery", headers = SecurityConstants.HEADER_FROM_IN)
	R<TableDataInfo<TokenVo>> getTokenPage(@SpringQueryMap TokenDTO tokenDTO);

	/**
	 * 删除token
	 * @param token token
	 * @return R<Void>
	 */
	@DeleteMapping(value = "/api/token/{token}", headers = SecurityConstants.HEADER_FROM_IN)
	R<Void> removeToken(@PathVariable("token") String token);

}
