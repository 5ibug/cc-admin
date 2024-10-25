package net.kegui.framework.jdbc.web.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.kegui.framework.core.application.page.PageDomain;
import net.kegui.framework.core.application.page.TableDataInfo;
import net.kegui.framework.core.application.page.TableSupport;
import net.kegui.framework.utils.TUtils;
import net.kegui.framework.utils.sql.SqlUtils;

import java.util.List;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 分页基础控制器
 */
public class PageUtils {

	/**
	 * 注入分页信息
	 */
	public static void startPage() {
		// 清除分页bug
		PageHelper.clearPage();
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer page = pageDomain.getCurrent();
		Integer pageSize = pageDomain.getPageSize();
		if (TUtils.isNotEmpty(page) && TUtils.isNotEmpty(pageSize)) {
			String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
			PageHelper.startPage(page, pageSize, orderBy);
		}
	}

	/**
	 * 响应请求分页数据
	 * @param list 数据列表
	 * @return 适应Json
	 */
	public static <T> TableDataInfo<T> getDataTable(List<T> list) {
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return TableDataInfo.page(list, pageInfo.getTotal());
	}

}
