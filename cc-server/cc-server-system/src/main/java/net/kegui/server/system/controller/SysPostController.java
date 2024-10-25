package net.kegui.server.system.controller;

import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import net.kegui.api.system.domain.SysPost;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.JsonResult;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.core.constants.UserConstants;
import net.kegui.framework.jdbc.web.utils.PageUtils;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.framework.security.utils.SecurityUtils;
import net.kegui.server.system.service.ISysPostService;
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
 * @Description: 岗位信息操作处理
 */
@Tag(description = "SysPostController", name = "岗位信息操作处理")
@RestController
@RequestMapping("/post")
public class SysPostController extends TWTController {

	@Autowired
	private ISysPostService iSysPostService;

	/**
	 * 新增岗位
	 * @param sysPost SysPost
	 * @return JsonResult<String>
	 */
	@Operation(summary = "新增岗位")
	@Log(service = "岗位管理", businessType = BusinessType.INSERT)
	@PostMapping
	@PreAuthorize("@role.hasPermi('system:post:insert')")
	public JsonResult<String> insert(@Validated @RequestBody SysPost sysPost) {
		if (UserConstants.NOT_UNIQUE.equals(iSysPostService.checkPostNameUnique(sysPost))) {
			return JsonResult.error("新增岗位'" + sysPost.getPostName() + "'失败，岗位名称已存在");
		}
		else if (UserConstants.NOT_UNIQUE.equals(iSysPostService.checkPostCodeUnique(sysPost))) {
			return JsonResult.error("新增岗位'" + sysPost.getPostName() + "'失败，岗位编码已存在");
		}
		sysPost.setCreateBy(SecurityUtils.getUsername());
		return json(iSysPostService.insertPost(sysPost));
	}

	/**
	 * 删除岗位
	 * @param postIds 唯一ID数组
	 * @return JsonResult<String>
	 */
	@Operation(summary = "删除岗位")
	@Log(service = "岗位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
	@PreAuthorize("@role.hasPermi('system:post:remove')")
	public JsonResult<String> remove(@PathVariable Long[] postIds) {
		return json(iSysPostService.deletePostByIds(postIds));
	}

	/**
	 * 修改岗位
	 * @param sysPost SysPost
	 * @return JsonResult<String>
	 */
	@Operation(summary = "修改岗位")
	@Log(service = "岗位管理", businessType = BusinessType.UPDATE)
	@PutMapping
	@PreAuthorize("@role.hasPermi('system:post:update')")
	public JsonResult<String> update(@Validated @RequestBody SysPost sysPost) {
		if (UserConstants.NOT_UNIQUE.equals(iSysPostService.checkPostNameUnique(sysPost))) {
			return JsonResult.error("修改岗位'" + sysPost.getPostName() + "'失败，岗位名称已存在");
		}
		else if (UserConstants.NOT_UNIQUE.equals(iSysPostService.checkPostCodeUnique(sysPost))) {
			return JsonResult.error("修改岗位'" + sysPost.getPostName() + "'失败，岗位编码已存在");
		}
		sysPost.setUpdateBy(SecurityUtils.getUsername());
		return json(iSysPostService.updatePost(sysPost));
	}

	/**
	 * 获取岗位列表
	 * @param post SysPost
	 * @return JsonResult<TableDataInfo>
	 */
	@Operation(summary = "获取岗位列表")
	@GetMapping("/pageQuery")
	@PreAuthorize("@role.hasPermi('system:post:list')")
	public JsonResult<TableDataInfo<SysPost>> pageQuery(SysPost post) {
		PageUtils.startPage();
		List<SysPost> list = iSysPostService.selectPostList(post);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 根据岗位编号获取详细信息
	 * @param postId 唯一ID
	 * @return JsonResult<SysPost>
	 */
	@Operation(summary = "根据岗位编号获取详细信息")
	@GetMapping("/{postId}")
	@PreAuthorize("@role.hasPermi('system:post:query')")
	public JsonResult<SysPost> getByPostId(@PathVariable Long postId) {
		return JsonResult.success(iSysPostService.selectPostById(postId));
	}

	/**
	 * 获取岗位选择框列表
	 * @return JsonResult<List<SysPost>>
	 */
	@Operation(summary = "获取岗位选择框列表")
	@GetMapping("/optionSelect")
	public JsonResult<List<SysPost>> optionSelect() {
		List<SysPost> posts = iSysPostService.selectPostAll();
		return JsonResult.success(posts);
	}

	/**
	 * 岗位管理数据导出
	 * @param sysPost SysPost
	 * @return List<SysPost>
	 */
	@ResponseExcel(name = "岗位管理")
	@Operation(summary = "数据导出")
	@Log(service = "岗位管理", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@PreAuthorize("@role.hasPermi('system:post:export')")
	public List<SysPost> export(@RequestBody SysPost sysPost) {
		return iSysPostService.selectPostList(sysPost);
	}

}
