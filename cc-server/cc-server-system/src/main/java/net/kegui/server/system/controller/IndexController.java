package net.kegui.server.system.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kegui.framework.core.application.domain.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Tag(description = "IndexController", name = "首页信息")
@RestController
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private NacosDiscoveryProperties discoveryProperties;

	@Value("${cc.version}")
	private String version;

	@GetMapping("info")
	public JsonResult<Map> info() throws NacosException {
		Map map = new HashMap();
		NamingService namingService = discoveryProperties.namingServiceInstance();
		Map<String, Integer> serviceCountMap = new ConcurrentHashMap<>();
		int pageSize = 10;
		int pageNo = 1;
		ListView<String> services;
		do {
			services = namingService.getServicesOfServer(pageNo++, pageSize);
			for (String service : services.getData()) {
				List<Instance> instances = namingService.selectInstances(service, true);
				serviceCountMap.put(service, instances.size());
			}
		}
		while (services.getCount() == pageSize);
		map.put("service", serviceCountMap);
		map.put("version", version);
		map.put("springVersion", org.springframework.boot.SpringBootVersion.getVersion());
		// 服务列表和数量
		return JsonResult.success(map);
	}

}
