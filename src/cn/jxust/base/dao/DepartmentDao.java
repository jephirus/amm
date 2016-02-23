package cn.jxust.base.dao;

import java.util.List;

import cn.jxust.base.model.Department;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao extends BaseDao<Department>
{

	public List<Department> findAll()
	{
		return findListCache("from Department order by departmentType, ordNum");
	}
	
	public PageData<Department> findAllByPage(int pageNum, Department entity)
	{
		PageData<Department> pageData = new PageData<Department>(pageNum);
		pageData = this.findPage(pageData, "from Department order by departmentId");
		return pageData;
	}
	
	public List<Department> findByType(String type)
	{
		return findList("from Department where departmentType = ? order by ordNum", type);
	}
	
	public List<Department> findByFixedQuota()
	{
		return findList("from Department where transFixedQuota = '1' order by ordNum");
	}
	
	/**
	 * 从缓存中获取同一类型单位的累计分值
	 * @param deptType
	 * @return
	 */
	public List<Department> findTotalScore(String deptType)
	{
		return findList("from Department where departmentType = ? and totalScore > 0 order by totalScore desc", deptType);
	}
}
