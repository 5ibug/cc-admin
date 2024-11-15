package net.kegui.server.ai;

import net.kegui.framework.core.annotation.EnableTwelveTConfig;
import net.kegui.framework.openfeign.annotation.EnableTWTFeignClients;
import net.kegui.framework.security.annotation.EnableTWTResourceServer;
import net.kegui.framework.swagger.annotation.EnableTwelveTSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTwelveTSwagger2
@EnableTWTResourceServer
@MapperScan("net.kegui.**.mapper")
@EnableTwelveTConfig
@EnableTWTFeignClients
@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

}