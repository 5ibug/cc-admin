package net.kegui.server.system.controller.api;

import net.kegui.api.system.domain.SysOperationLog;
import net.kegui.framework.security.annotation.AuthIgnore;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.server.system.service.ISysOperationLogService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 操作日志记录
 */
@Hidden
@Tag(description = "SysOperationLogApi", name = "操作日志记录API")
@RestController
@RequestMapping("/api/operationLog")
public class SysOperationLogApi extends TWTController {

	@Autowired
	private ISysOperationLogService iSysOperationLogService;

	/**
	 * 新增操作日志
	 * @param operationLog SysOperationLog
	 */
	@Operation(summary = "新增操作日志")
	@AuthIgnore
	@PostMapping
	public void saveLog(@RequestBody SysOperationLog operationLog) {
		iSysOperationLogService.insertOperationLog(operationLog);
	}

}
