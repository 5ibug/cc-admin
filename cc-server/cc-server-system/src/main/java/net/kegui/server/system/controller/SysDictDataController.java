package net.kegui.server.system.controller;

import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import net.kegui.api.system.domain.SysDictData;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.JsonResult;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.jdbc.web.utils.PageUtils;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.framework.security.utils.SecurityUtils;
import net.kegui.server.system.service.ISysDictDataService;
import net.kegui.server.system.service.ISysDictTypeService;
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
 * @Description: 数据字典信息
 */
@Tag(description = "SysDictDataController", name = "数据字典信息")
@RestController
@RequestMapping("/dictionaries/data")
public class SysDictDataController extends TWTController {

	@Autowired
	private ISysDictDataService dictDataService;

	@Autowired
	private ISysDictTypeService dictTypeService;

	/**
	 * 分页查询数据字典
	 * @param sysDictData SysDictData
	 * @return JsonResult<TableDataInfo>
	 */
	@Operation(summary = "分页查询数据字典")
	@GetMapping("/pageQuery")
	@PreAuthorize("@role.hasPermi('system:dict:list')")
	public JsonResult<TableDataInfo<SysDictData>> pageQuery(SysDictData sysDictData) {
		PageUtils.startPage();
		List<SysDictData> list = dictDataService.selectDictDataList(sysDictData);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 导出数据字典excel
	 * @param sysDictData SysDictData
	 * @return List<SysDictData>
	 */
	@ResponseExcel(name = "字典数据")
	@Operation(summary = "导出数据字典excel")
	@Log(service = "字典数据", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@PreAuthorize("@role.hasPermi('system:dict:export')")
	public List<SysDictData> exportExcel(@RequestBody SysDictData sysDictData) {
		return dictDataService.selectDictDataList(sysDictData);
	}

	/**
	 * 查询字典数据详细
	 * @param dictCode 数据字典Code
	 * @return JsonResult<SysDictData>
	 */
	@Operation(summary = "查询字典数据详细")
	@GetMapping(value = "/{dictCode}")
	@PreAuthorize("@role.hasPermi('system:dict:query')")
	public JsonResult<SysDictData> getDictDataById(@PathVariable Long dictCode) {
		return JsonResult.success(dictDataService.selectDictDataById(dictCode));
	}

	/**
	 * 根据字典类型查询字典数据信息
	 * @param dictType 字典类型
	 * @return JsonResult<List<SysDictData>>
	 */
	@Operation(summary = "根据字典类型查询字典数据信息")
	@GetMapping(value = "/type/{dictType}")
	public JsonResult<List<SysDictData>> dictType(@PathVariable String dictType) {
		return JsonResult.success(dictTypeService.selectDictDataByType(dictType));
	}

	/**
	 * 新增字典类型
	 * @param sysDictData SysDictData
	 * @return JsonResult<String>
	 */
	@Operation(summary = "新增字典类型")
	@Log(service = "字典数据", businessType = BusinessType.INSERT)
	@PostMapping
	@PreAuthorize("@role.hasPermi('system:dict:insert')")
	public JsonResult<String> insert(@Validated @RequestBody SysDictData sysDictData) {
		sysDictData.setCreateBy(SecurityUtils.getUsername());
		return json(dictDataService.insertDictData(sysDictData));
	}

	/**
	 * 修改保存字典类型
	 * @param sysDictData SysDictData
	 * @return JsonResult<String>
	 */
	@Operation(summary = "修改保存字典类型")
	@Log(service = "字典数据", businessType = BusinessType.UPDATE)
	@PutMapping
	@PreAuthorize("@role.hasPermi('system:dict:update')")
	public JsonResult<String> update(@Validated @RequestBody SysDictData sysDictData) {
		sysDictData.setUpdateBy(SecurityUtils.getUsername());
		return json(dictDataService.updateDictData(sysDictData));
	}

	/**
	 * 删除字典类型
	 * @param dictCodes 字典类型Codes
	 * @return JsonResult<String>
	 */
	@Operation(summary = "删除字典类型")
	@Log(service = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictCodes}")
	@PreAuthorize("@role.hasPermi('system:dict:remove')")
	public JsonResult<String> remove(@PathVariable Long[] dictCodes) {
		return json(dictDataService.deleteDictDataByIds(dictCodes));
	}

}
