package net.kegui.framework.datasource.annotation;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: Sharding JDBC数据源
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("shardingSphere")
public @interface ShardingDatasource {

}
