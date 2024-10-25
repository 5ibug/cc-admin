package net.kegui.server.system.controller;

import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import net.kegui.api.system.domain.SysRole;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.JsonResult;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.core.constants.UserConstants;
import net.kegui.framework.jdbc.web.utils.PageUtils;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.framework.security.utils.SecurityUtils;
import net.kegui.server.system.service.ISysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 角色信息
 */
@Tag(description = "SysRoleController", name = "角色信息")
@RestController
@RequestMapping("/role")
public class SysRoleController extends TWTController {

	@Autowired
	private ISysRoleService iSysRoleService;

	/**
	 * 角色信息分页查询
	 * @param role SysRole
	 * @return JsonResult<TableDataInfo>
	 */
	@Operation(summary = "角色信息分页查询")
	@GetMapping("/pageQuery")
	@PreAuthorize("@role.hasPermi('system:role:list')")
	public JsonResult<TableDataInfo<SysRole>> pageQuery(SysRole role) {
		PageUtils.startPage();
		List<SysRole> list = iSysRoleService.selectRoleList(role);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 数据导出
	 * @param role SysRole
	 * @return List<SysRole>
	 */
	@ResponseExcel(name = "角色管理")
	@Operation(summary = "数据导出")
	@Log(service = "角色管理", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@PreAuthorize("@role.hasPermi('system:role:export')")
	public List<SysRole> export(@RequestBody SysRole role) {
		return iSysRoleService.selectRoleList(role);
	}

	/**
	 * 根据角色编号获取详细信息
	 * @param roleId 角色ID
	 * @return JsonResult<SysRole>
	 */
	@Operation(summary = "根据角色编号获取详细信息")
	@GetMapping("/{roleId}")
	@PreAuthorize("@role.hasPermi('system:role:query')")
	public JsonResult<SysRole> getInfo(@PathVariable Long roleId) {
		iSysRoleService.checkRoleDataScope(roleId);
		return JsonResult.success(iSysRoleService.selectRoleById(roleId));
	}

	/**
	 * 新增角色
	 * @param role SysRole
	 * @return JsonResult<String>
	 */
	@Operation(summary = "新增角色")
	@Log(service = "角色管理", businessType = BusinessType.INSERT)
	@PostMapping
	@PreAuthorize("@role.hasPermi('system:role:insert')")
	public JsonResult<String> insert(@Validated @RequestBody SysRole role) {
		if (UserConstants.NOT_UNIQUE.equals(iSysRoleService.checkRoleNameUnique(role))) {
			return JsonResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
		}
		else if (UserConstants.NOT_UNIQUE.equals(iSysRoleService.checkRoleKeyUnique(role))) {
			return JsonResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
		}
		role.setCreateBy(SecurityUtils.getUsername());
		return json(iSysRoleService.insertRole(role));
	}

	/**
	 * 修改保存角色
	 * @param role SysRole
	 * @return JsonResult<String>
	 */
	@Operation(summary = "修改保存角色")
	@Log(service = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping
	@PreAuthorize("@role.hasPermi('system:role:update')")
	public JsonResult<String> update(@Validated @RequestBody SysRole role) {
		iSysRoleService.checkRoleAllowed(role);
		iSysRoleService.checkRoleDataScope(role.getRoleId());
		if (UserConstants.NOT_UNIQUE.equals(iSysRoleService.checkRoleNameUnique(role))) {
			return JsonResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
		}
		else if (UserConstants.NOT_UNIQUE.equals(iSysRoleService.checkRoleKeyUnique(role))) {
			return JsonResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
		}
		role.setUpdateBy(SecurityUtils.getUsername());
		return json(iSysRoleService.updateRole(role));
	}

	/**
	 * 状态修改
	 * @param role SysRole
	 * @return JsonResult<String>
	 */
	@Operation(summary = "状态修改")
	@Log(service = "角色管理", businessType = BusinessType.UPDATE)
	@PutMapping("/changeStatus")
	@PreAuthorize("@role.hasPermi('system:role:update')")
	public JsonResult<String> changeStatus(@RequestBody SysRole role) {
		iSysRoleService.checkRoleAllowed(role);
		role.setUpdateBy(SecurityUtils.getUsername());
		return json(iSysRoleService.updateRoleStatus(role));
	}

	/**
	 * 删除角色
	 */
	@Operation(summary = "删除角色")
	@Log(service = "角色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
	@PreAuthorize("@role.hasPermi('system:role:remove')")
	public JsonResult<String> remove(@PathVariable Long[] roleIds) {
		return json(iSysRoleService.deleteRoleByIds(roleIds));
	}

	/**
	 * 获取角色选择框列表
	 */
	@Operation(summary = "获取角色选择框列表")
	@GetMapping("/optionSelect")
	@PreAuthorize("@role.hasPermi('system:role:query')")
	public JsonResult<List<SysRole>> optionSelect() {
		return JsonResult.success(iSysRoleService.selectRoleAll());
	}

}
