package net.kegui.server.system.controller.api;

import net.kegui.api.system.domain.SysUser;
import net.kegui.api.system.model.UserInfo;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.domain.R;
import net.kegui.framework.security.annotation.AuthIgnore;
import net.kegui.framework.utils.TUtils;
import net.kegui.server.system.service.ISysPermissionService;
import net.kegui.server.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 用户信息
 */
@Hidden
@Tag(description = "SysUserApi", name = "用户信息API")
@RestController
@RequestMapping("/api/user")
public class SysUserApi extends TWTController {

	@Autowired
	private ISysUserService iSysUserService;

	@Autowired
	private ISysPermissionService iSysPermissionService;

	/**
	 * 获取当前用户信息(认证中心服务专用)
	 * @param username String
	 * @return R<UserInfo>
	 */
	@Operation(summary = "获取当前用户信息(认证中心服务专用)")
	@AuthIgnore
	@GetMapping("/info/{username}")
	public R<UserInfo> info(@PathVariable("username") String username) {
		SysUser sysUser = iSysUserService.selectUserByUserName(username, false);
		if (TUtils.isEmpty(sysUser)) {
			return R.fail("用户名或密码错误");
		}
		// 角色集合
		Set<String> roles = iSysPermissionService.getRolePermission(sysUser.getUserId());
		// 权限集合
		Set<String> permissions = iSysPermissionService.getMenuPermission(sysUser.getUserId());
		UserInfo sysUserVo = new UserInfo();
		sysUserVo.setSysUser(sysUser);
		sysUserVo.setRoles(roles);
		sysUserVo.setPermissions(permissions);
		return R.ok(sysUserVo);
	}

}
