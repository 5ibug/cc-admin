package net.kegui.server.gen.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.api.gen.domain.GenTemplate;
import net.kegui.server.gen.service.IGenTemplateService;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.AjaxResult;
import net.kegui.framework.core.application.domain.JsonResult;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import net.kegui.framework.jdbc.web.utils.PageUtils;

/**
 * 代码生成业务模板Controller
 *
 * @author WangZhen
 * @WebSite admin@5ibug.net
 */
@Tag(description = "GenTemplateController", name = "代码生成业务模板")
@RestController
@RequestMapping("/template")
public class GenTemplateController extends TWTController {

	@Autowired
	private IGenTemplateService genTemplateService;

	/**
	 * 查询代码生成业务模板分页
	 */
	@Operation(summary = "查询代码生成业务模板分页")
	@PreAuthorize("@role.hasPermi('gen:template:list')")
	@GetMapping("/pageQuery")
	public JsonResult<TableDataInfo<GenTemplate>> pageQuery(GenTemplate genTemplate) {
		PageUtils.startPage();
		List<GenTemplate> list = genTemplateService.selectGenTemplateList(genTemplate);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 获取代码生成业务模板详细信息
	 */
	@Operation(summary = "获取代码生成业务模板详细信息")
	@PreAuthorize("@role.hasPermi('gen:template:query')")
	@GetMapping(value = "/{id}")
	public JsonResult<GenTemplate> getInfo(@PathVariable("id") Long id) {
		return JsonResult.success(genTemplateService.selectGenTemplateById(id));
	}

	/**
	 * 新增代码生成业务模板
	 */
	@Operation(summary = "新增代码生成业务模板")
	@PreAuthorize("@role.hasPermi('gen:template:add')")
	@Log(service = "代码生成业务模板", businessType = BusinessType.INSERT)
	@PostMapping
	public JsonResult<String> add(@RequestBody GenTemplate genTemplate) {
		return json(genTemplateService.insertGenTemplate(genTemplate));
	}

	/**
	 * 修改代码生成业务模板
	 */
	@Operation(summary = "修改代码生成业务模板")
	@PreAuthorize("@role.hasPermi('gen:template:edit')")
	@Log(service = "代码生成业务模板", businessType = BusinessType.UPDATE)
	@PutMapping
	public JsonResult<String> edit(@RequestBody GenTemplate genTemplate) {
		return json(genTemplateService.updateGenTemplate(genTemplate));
	}

	/**
	 * 删除代码生成业务模板
	 */
	@Operation(summary = "删除代码生成业务模板")
	@PreAuthorize("@role.hasPermi('gen:template:remove')")
	@Log(service = "代码生成业务模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public JsonResult<String> remove(@PathVariable Long[] ids) {
		return json(genTemplateService.deleteGenTemplateByIds(ids));
	}

}
