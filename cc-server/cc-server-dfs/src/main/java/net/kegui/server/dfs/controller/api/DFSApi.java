package net.kegui.server.dfs.controller.api;

import net.kegui.api.dfs.domain.SysDfs;
import net.kegui.api.dfs.domain.SysFile;
import net.kegui.framework.core.application.controller.TWTController;
import net.kegui.framework.core.domain.R;
import net.kegui.framework.security.annotation.AuthIgnore;
import net.kegui.framework.utils.file.FileUtils;
import net.kegui.server.dfs.service.IDFSService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 文件请求处理API
 */
@Hidden
@AuthIgnore
@RestController
@RequestMapping("/api")
public class DFSApi extends TWTController {

	@Autowired
	private IDFSService sysFileService;

	/**
	 * 系统单文件上传API
	 * @param file MultipartFile
	 * @return R<SysFile>
	 */
	@PostMapping("/upload")
	public R<SysFile> upload(MultipartFile file) {
		// 上传并返回访问地址
		SysDfs sysDfs = sysFileService.uploadFile(file);

		String path = sysDfs.getPath();

		SysFile sysFile = new SysFile();
		sysFile.setName(FileUtils.getName(path));
		sysFile.setUrl(path);

		return R.ok(sysFile);
	}

}
