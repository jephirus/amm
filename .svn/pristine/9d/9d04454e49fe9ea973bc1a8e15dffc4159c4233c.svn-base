package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class AreaDao extends BaseDao<Area>{

	/**
	 * 
	 * @Description:分页查找所有区域
	 * @author:laiy
	 * @date:2015年10月18日下午3:56:36 
	 * @param: @param pageNum
	 * @param: @param entity
	 * @throws
	 */
	public PageData<Area> findAllByPage(int pageNum, Area entity, Department department)
	{
		PageData<Area> pageData = new PageData<Area>(pageNum);
		pageData = this.findPage(pageData, "from Area where department=?", department);
		return pageData;
	}
	
	public PageData<Area> findAllByPage(int pageNum, Area entity)
	{
		PageData<Area> pageData = new PageData<Area>(pageNum);
		pageData = this.findPage(pageData, "from Area");
		return pageData;
	}
	
	public List<Area> findAll()
	{
		return this.findList("from Area");
	}
}
