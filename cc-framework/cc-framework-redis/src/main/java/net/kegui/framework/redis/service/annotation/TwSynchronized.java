package net.kegui.framework.redis.service.annotation;

import java.lang.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 分布式锁（不支持重入）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TwSynchronized {

	/**
	 * 唯一锁名称
	 */
	String value();

}
