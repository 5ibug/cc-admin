package net.kegui.server.system.service;

import java.util.List;

import net.kegui.api.i18n.domain.I18n;

/**
 * 国际化Service接口
 *
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @date 2024-03-28
 */
public interface II18nService {

	/**
	 * 查询国际化
	 * @param i18nId 国际化主键
	 * @return 国际化
	 */
	public I18n selectI18nByI18nId(Long i18nId);

	/**
	 * 查询国际化列表
	 * @param i18n 国际化
	 * @return 国际化集合
	 */
	public List<I18n> selectI18nList(I18n i18n);

	/**
	 * 新增国际化
	 * @param i18n 国际化
	 * @return 结果
	 */
	public int insertI18n(I18n i18n);

	/**
	 * 修改国际化
	 * @param i18n 国际化
	 * @return 结果
	 */
	public int updateI18n(I18n i18n);

	/**
	 * 批量删除国际化
	 * @param i18nIds 需要删除的国际化主键集合
	 * @return 结果
	 */
	public int deleteI18nByI18nIds(Long[] i18nIds);

	/**
	 * 删除国际化信息
	 * @param i18nId 国际化主键
	 * @return 结果
	 */
	public int deleteI18nByI18nId(Long i18nId);

}
