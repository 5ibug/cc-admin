package net.kegui.server.system.controller;

import net.kegui.api.system.domain.SysClientDetails;
import net.kegui.api.system.domain.dto.SysClientDetailsDTO;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.JsonResult;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.jdbc.web.utils.PageUtils;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.framework.utils.StringUtils;
import net.kegui.framework.utils.TUtils;
import net.kegui.server.system.service.ISysClientDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 终端配置 信息操作处理
 */
@Tag(description = "Oauth2ClientDetailsController", name = "终端配置")
@RestController
@RequestMapping("/client")
public class Oauth2ClientDetailsController extends TWTController {

	@Autowired
	private ISysClientDetailsService sysClientDetailsService;

	/**
	 * 查询终端配置列表
	 * @param sysClientDetailsDTO SysClientDetails
	 * @return JsonResult<TableDataInfo>
	 */
	@Operation(summary = "查询终端配置列表")
	@PreAuthorize("@role.hasPermi('system:client:list')")
	@GetMapping("/pageQuery")
	public JsonResult<TableDataInfo<SysClientDetails>> pageQuery(SysClientDetailsDTO sysClientDetailsDTO) {
		PageUtils.startPage();
		List<SysClientDetails> list = sysClientDetailsService.selectSysClientDetailsList(sysClientDetailsDTO);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 获取终端配置详细信息
	 * @param clientId 终端ID
	 * @return JsonResult<SysClientDetails>
	 */
	@Operation(summary = "获取终端配置详细信息")
	@PreAuthorize("@role.hasPermi('system:client:query')")
	@GetMapping(value = "/{clientId}")
	public JsonResult<SysClientDetails> getInfo(@PathVariable("clientId") String clientId) {
		return JsonResult.success(sysClientDetailsService.selectSysClientDetailsById(clientId));
	}

	/**
	 * 新增终端配置
	 * @param sysClientDetails SysClientDetails
	 * @return JsonResult<String>
	 */
	@Operation(summary = "新增终端配置")
	@PreAuthorize("@role.hasPermi('system:client:insert')")
	@Log(service = "终端配置", businessType = BusinessType.INSERT)
	@PostMapping
	public JsonResult<String> insert(@RequestBody SysClientDetails sysClientDetails) {
		String clientId = sysClientDetails.getClientId();
		if (StringUtils.isNotNull(sysClientDetailsService.selectSysClientDetailsById(clientId))) {
			return JsonResult.error("新增终端'" + clientId + "'失败，编号已存在");
		}
		sysClientDetails.setClientSecret(sysClientDetails.getClientSecret());
		return json(sysClientDetailsService.insertSysClientDetails(sysClientDetails));
	}

	/**
	 * 修改终端配置
	 * @param sysClientDetails sysClientDetails
	 * @return JsonResult<String>
	 */
	@Operation(summary = "修改终端配置")
	@PreAuthorize("@role.hasPermi('system:client:update')")
	@Log(service = "终端配置", businessType = BusinessType.UPDATE)
	@PutMapping
	public JsonResult<String> update(@RequestBody SysClientDetails sysClientDetails) {
		// 重新设置密码
		if (TUtils.isNotEmpty(sysClientDetails.getClientSecret())) {
			sysClientDetails.setClientSecret(sysClientDetails.getClientSecret());
		}

		return json(sysClientDetailsService.updateSysClientDetails(sysClientDetails));
	}

	/**
	 * 删除终端配置
	 * @param clientIds 终端ID数组
	 * @return 成功删除个数
	 */
	@Operation(summary = "删除终端配置")
	@PreAuthorize("@role.hasPermi('system:client:remove')")
	@Log(service = "终端配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clientIds}")
	public JsonResult<String> remove(@PathVariable String[] clientIds) {
		return json(sysClientDetailsService.deleteSysClientDetailsByIds(clientIds));
	}

}
