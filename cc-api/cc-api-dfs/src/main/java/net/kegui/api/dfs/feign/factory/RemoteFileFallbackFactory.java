package net.kegui.api.dfs.feign.factory;

import net.kegui.api.dfs.feign.RemoteFileService;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: DFS服务降级处理
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

	@Override
	public RemoteFileService create(Throwable throwable) {
		log.error("文件服务调用失败:{}", throwable.getMessage());
		return (file) -> R.fail("上传文件失败:" + throwable.getMessage());
	}

}
