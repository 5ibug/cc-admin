package net.kegui.auth;

import net.kegui.framework.openfeign.annotation.EnableTWTFeignClients;
import net.kegui.framework.core.annotation.EnableTwelveTConfig;
import net.kegui.framework.swagger.annotation.EnableTwelveTSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 认证中心启动器
 */
@EnableTwelveTSwagger2
@EnableTwelveTConfig
@EnableTWTFeignClients
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
