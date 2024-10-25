package net.kegui.framework.log.event.listener;

import net.kegui.api.system.domain.SysOperationLog;
import net.kegui.api.system.feign.RemoteLogService;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.log.event.SysOperationLogEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 异步监听系统登录日志事件
 */
public class SysSysOperationLogEventLogListener {

	private final RemoteLogService remoteLogService;

	public SysSysOperationLogEventLogListener(RemoteLogService remoteLogService) {
		this.remoteLogService = remoteLogService;
	}

	@Async
	@Order
	@EventListener(SysOperationLogEvent.class)
	public void saveSysLog(SysOperationLogEvent event) {
		SysOperationLog sysOperationLog = (SysOperationLog) event.getSource();
		remoteLogService.saveLog(sysOperationLog);
	}

}
