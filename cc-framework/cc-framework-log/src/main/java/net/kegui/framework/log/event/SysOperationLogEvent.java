package net.kegui.framework.log.event;

import net.kegui.api.system.domain.SysLoginInfo;
import net.kegui.api.system.domain.SysOperationLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 系统操作日志事件
 */
public class SysOperationLogEvent extends ApplicationEvent {

	public SysOperationLogEvent(SysOperationLog source) {
		super(source);
	}

}
