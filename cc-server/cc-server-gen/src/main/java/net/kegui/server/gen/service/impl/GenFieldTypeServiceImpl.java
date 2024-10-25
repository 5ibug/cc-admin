package net.kegui.server.gen.service.impl;

import java.util.List;
import net.kegui.framework.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import net.kegui.framework.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import net.kegui.server.gen.mapper.GenFieldTypeMapper;
import net.kegui.api.gen.domain.GenFieldType;
import net.kegui.server.gen.service.IGenFieldTypeService;

/**
 * 字段类型管理Service业务层处理
 *
 * @author WangZhen
 * @WebSite admin@5ibug.net
 */
@Service
public class GenFieldTypeServiceImpl implements IGenFieldTypeService {

	@Autowired
	private GenFieldTypeMapper genFieldTypeMapper;

	/**
	 * 查询字段类型管理
	 * @param id 字段类型管理主键
	 * @return 字段类型管理
	 */
	@Override
	public GenFieldType selectGenFieldTypeById(Long id) {
		return genFieldTypeMapper.selectGenFieldTypeById(id);
	}

	/**
	 * 查询字段类型管理列表
	 * @param genFieldType 字段类型管理
	 * @return 字段类型管理
	 */
	@Override
	public List<GenFieldType> selectGenFieldTypeList(GenFieldType genFieldType) {
		return genFieldTypeMapper.selectGenFieldTypeList(genFieldType);
	}

	/**
	 * 新增字段类型管理
	 * @param genFieldType 字段类型管理
	 * @return 结果
	 */
	@Override
	public int insertGenFieldType(GenFieldType genFieldType) {
		genFieldType.setCreateTime(DateUtils.getNowDate());
		String loginUsername = SecurityUtils.getUsername();
		genFieldType.setCreateBy(loginUsername);
		genFieldType.setUpdateBy(loginUsername);
		return genFieldTypeMapper.insertGenFieldType(genFieldType);
	}

	/**
	 * 修改字段类型管理
	 * @param genFieldType 字段类型管理
	 * @return 结果
	 */
	@Override
	public int updateGenFieldType(GenFieldType genFieldType) {
		genFieldType.setUpdateTime(DateUtils.getNowDate());
		String loginUsername = SecurityUtils.getUsername();
		genFieldType.setCreateBy(loginUsername);
		genFieldType.setUpdateBy(loginUsername);
		return genFieldTypeMapper.updateGenFieldType(genFieldType);
	}

	/**
	 * 批量删除字段类型管理
	 * @param ids 需要删除的字段类型管理主键
	 * @return 结果
	 */
	@Override
	public int deleteGenFieldTypeByIds(Long[] ids) {
		return genFieldTypeMapper.deleteGenFieldTypeByIds(ids);
	}

	/**
	 * 删除字段类型管理信息
	 * @param id 字段类型管理主键
	 * @return 结果
	 */
	@Override
	public int deleteGenFieldTypeById(Long id) {
		return genFieldTypeMapper.deleteGenFieldTypeById(id);
	}

}
