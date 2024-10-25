package net.kegui.server.job;

import net.kegui.framework.openfeign.annotation.EnableTWTFeignClients;
import net.kegui.framework.core.annotation.EnableTwelveTConfig;
import net.kegui.framework.security.annotation.EnableTWTResourceServer;
import net.kegui.framework.swagger.annotation.EnableTwelveTSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 启动程序
 * @EnableFeignClients 开启服务扫描
 */
@EnableTwelveTSwagger2
@EnableTWTResourceServer
@MapperScan("net.kegui.**.mapper")
@EnableTwelveTConfig
@EnableTWTFeignClients
@SpringBootApplication
public class JobApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

}
