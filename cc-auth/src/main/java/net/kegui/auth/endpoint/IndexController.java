package net.kegui.auth.endpoint;

import net.kegui.framework.core.application.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 测试第三方登录
 */
@Tag(description = "IndexController", name = "首页显示内容Test")
@RestController
public class IndexController {

	@Operation(summary = "Test获取当前登录用户信息OAuth2")
	@GetMapping("/")
	public AjaxResult callback(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return AjaxResult.success(oAuth2AuthenticationToken);
	}

}
