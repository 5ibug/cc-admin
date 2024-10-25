package net.kegui.framework.log.event;

import net.kegui.api.system.domain.SysLoginInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 系统登录日志事件
 */
public class SysLoginLogEvent extends ApplicationEvent {

	public SysLoginLogEvent(SysLoginInfo source) {
		super(source);
	}

}
