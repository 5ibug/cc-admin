package net.kegui.server.system.controller.api;

import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.framework.security.annotation.AuthIgnore;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.utils.http.IpUtils;
import net.kegui.server.system.service.ISysLoginInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 系统操作/访问日志
 */
@Hidden
@Tag(description = "SysLoginInfoApi", name = "系统操作日志API")
@RestController
@RequestMapping("/api/loginInfo")
public class SysLoginInfoApi extends TWTController {

	@Autowired
	private ISysLoginInfoService iSysLoginInfoService;

	/**
	 * 记录登录信息
	 * @param sysLoginInfo SysLoginInfo
	 */
	@Operation(summary = "记录登录信息")
	@AuthIgnore
	@PostMapping
	public void insertLog(@RequestBody SysLoginInfo sysLoginInfo) {
		iSysLoginInfoService.insertLoginInfo(sysLoginInfo);
	}

}
