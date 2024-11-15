package net.kegui.server.job.controller;

import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import net.kegui.api.job.domain.SysJobLog;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.application.domain.JsonResult;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.jdbc.web.utils.PageUtils;
import net.kegui.framework.log.annotation.Log;
import net.kegui.framework.log.enums.BusinessType;
import net.kegui.server.job.service.ISysJobLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 调度日志操作处理
 */
@Tag(description = "SysJobLogController", name = "调度日志操作处理")
@RestController
@RequestMapping("/log")
public class SysJobLogController extends TWTController {

	@Autowired
	private ISysJobLogService jobLogService;

	/**
	 * 查询定时任务调度日志列表
	 * @param sysJobLog SysJobLog
	 * @return JsonResult
	 */
	@Operation(summary = "查询定时任务调度日志列表")
	@GetMapping("/pageQuery")
	@PreAuthorize("@role.hasPermi('system:job:list')")
	public JsonResult<TableDataInfo<SysJobLog>> pageQuery(SysJobLog sysJobLog) {
		PageUtils.startPage();
		List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
		return JsonResult.success(PageUtils.getDataTable(list));
	}

	/**
	 * 导出定时任务调度日志列表
	 * @param sysJobLog SysJobLog
	 * @return List<SysJobLog>
	 */
	@ResponseExcel(name = "任务调度日志")
	@Operation(summary = "导出定时任务调度日志列表")
	@Log(service = "任务调度日志", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@PreAuthorize("@role.hasPermi('system:job:export')")
	public List<SysJobLog> export(@RequestBody SysJobLog sysJobLog) {
		return jobLogService.selectJobLogList(sysJobLog);
	}

	/**
	 * 根据调度编号获取详细信息
	 * @param jobLogId id
	 * @return JsonResult
	 */
	@Operation(summary = "根据调度编号获取详细信息")
	@GetMapping("/{jobLogId}")
	@PreAuthorize("@role.hasPermi('system:job:query')")
	public JsonResult<SysJobLog> getInfo(@PathVariable Long jobLogId) {
		return JsonResult.success(jobLogService.selectJobLogById(jobLogId));
	}

	/**
	 * 删除定时任务调度日志
	 * @param jobLogIds 数组id
	 * @return JsonResult
	 */
	@Operation(summary = "删除定时任务调度日志")
	@Log(service = "定时任务调度日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobLogIds}")
	@PreAuthorize("@role.hasPermi('system:job:remove')")
	public JsonResult<String> remove(@PathVariable Long[] jobLogIds) {
		return json(jobLogService.deleteJobLogByIds(jobLogIds));
	}

	/**
	 * 清空定时任务调度日志
	 * @return JsonResult
	 */
	@Operation(summary = "清空定时任务调度日志")
	@Log(service = "调度日志", businessType = BusinessType.CLEAN)
	@DeleteMapping("/clean")
	@PreAuthorize("@role.hasPermi('system:job:remove')")
	public JsonResult<String> clean() {
		jobLogService.cleanJobLog();
		return JsonResult.success();
	}

}
