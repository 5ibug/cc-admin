package net.kegui.framework.utils.function;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 函数式接口
 */
@FunctionalInterface
public interface CheckedConsumer<T> extends Serializable {

	/**
	 * Run the Consumer
	 * @param var1 T
	 * @throws Throwable UncheckedException
	 */

	@Nullable
	void accept(@Nullable T var1) throws Throwable;

}
