package net.kegui.framework.log.event.listener;

import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.api.system.feign.RemoteLogService;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.log.event.SysLoginLogEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 异步监听系统登录日志事件
 */
public class SysLoginLogListener {

	private final RemoteLogService remoteLogService;

	public SysLoginLogListener(RemoteLogService remoteLogService) {
		this.remoteLogService = remoteLogService;
	}

	@Async
	@Order
	@EventListener(SysLoginLogEvent.class)
	public void saveSysLog(SysLoginLogEvent event) {
		SysLoginInfo sysLoginInfo = (SysLoginInfo) event.getSource();
		remoteLogService.saveLoginInfo(sysLoginInfo);
	}

}
