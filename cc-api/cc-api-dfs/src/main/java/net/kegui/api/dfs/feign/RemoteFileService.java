package net.kegui.api.dfs.feign;

import net.kegui.api.dfs.domain.SysFile;
import net.kegui.api.dfs.feign.factory.RemoteFileFallbackFactory;
import net.kegui.framework.core.constants.SecurityConstants;
import net.kegui.framework.core.constants.ServiceNameConstants;
import net.kegui.framework.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: DFS服务
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE,
		fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {

	/**
	 * 上传文件
	 * @param file 文件信息
	 * @return 结果
	 */
	@PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			headers = SecurityConstants.HEADER_FROM_IN)
	R<SysFile> upload(@RequestPart(value = "file") MultipartFile file);

}
