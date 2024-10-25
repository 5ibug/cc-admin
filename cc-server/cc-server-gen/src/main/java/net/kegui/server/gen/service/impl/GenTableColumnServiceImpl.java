package net.kegui.server.gen.service.impl;

import java.util.List;

import net.kegui.api.gen.domain.GenGroup;
import net.kegui.api.gen.domain.GenTableColumn;
import net.kegui.api.gen.domain.GenTemplateGroup;
import net.kegui.framework.utils.Convert;
import net.kegui.server.gen.mapper.GenGroupMapper;
import net.kegui.server.gen.mapper.GenTableColumnMapper;
import net.kegui.server.gen.service.IGenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WangZhen
 * @WebSite admin@5ibug.net
 * @Description: 业务字段 服务层实现
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {

	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	@Autowired
	private GenGroupMapper genGroupMapper;

	/**
	 * 查询业务字段列表
	 * @param tableId 业务字段编号
	 * @return 业务字段集合
	 */
	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
		return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}

	/**
	 * 新增业务字段
	 * @param genTableColumn 业务字段信息
	 * @return 结果
	 */
	@Override
	public int insertGenTableColumn(GenTableColumn genTableColumn) {
		return genTableColumnMapper.insertGenTableColumn(genTableColumn);
	}

	/**
	 * 修改业务字段
	 * @param genTableColumn 业务字段信息
	 * @return 结果
	 */
	@Override
	public int updateGenTableColumn(GenTableColumn genTableColumn) {
		return genTableColumnMapper.updateGenTableColumn(genTableColumn);
	}

	/**
	 * 删除业务字段对象
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteGenTableColumnByIds(String ids) {
		return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
	}

	/**
	 * 查询代码生成业务所有模板分组列表
	 * @return List GenTemplate
	 */
	@Override
	public List<GenGroup> selectGenGroupAll() {
		return genGroupMapper.selectGenGroupAll();
	}

}
