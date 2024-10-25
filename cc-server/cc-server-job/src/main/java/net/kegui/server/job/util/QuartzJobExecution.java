package net.kegui.server.job.util;

import net.kegui.api.job.domain.SysJob;
import org.quartz.JobExecutionContext;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 定时任务工具类
 */
public class QuartzJobExecution extends AbstractQuartzJob {

	@Override
	protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
		JobInvokeUtil.invokeMethod(sysJob);
	}

}
