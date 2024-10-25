package net.kegui.framework.security.annotation;

import net.kegui.framework.security.config.TWTResourceServerAutoConfiguration;
import net.kegui.framework.security.config.TWTResourceServerConfiguration;
import net.kegui.framework.security.feign.FeignConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.lang.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 资源服务注解
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableMethodSecurity // 开启全局注解安全
@Import({ TWTResourceServerAutoConfiguration.class, TWTResourceServerConfiguration.class, FeignConfig.class })
public @interface EnableTWTResourceServer {

}
