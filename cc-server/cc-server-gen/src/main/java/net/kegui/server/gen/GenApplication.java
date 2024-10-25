package net.kegui.server.gen;

import net.kegui.framework.openfeign.annotation.EnableTWTFeignClients;
import net.kegui.framework.core.annotation.EnableTwelveTConfig;
import net.kegui.framework.datasource.annotation.EnableDynamicDataSource;
import net.kegui.framework.security.annotation.EnableTWTResourceServer;
import net.kegui.framework.swagger.annotation.EnableTwelveTSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 启动器
 */
@EnableDynamicDataSource
@EnableTwelveTSwagger2
@EnableTWTResourceServer
@MapperScan("net.kegui.**.mapper")
@EnableTwelveTConfig
@EnableTWTFeignClients
@SpringBootApplication
public class GenApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenApplication.class, args);
	}

}
