package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.DepartmentDao;
import cn.jxust.base.model.Department;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class DepartmentService extends BaseService<Department>
{
	@Override
	@Resource(name="departmentDao")
	public void setBaseDao(BaseDao<Department> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public DepartmentDao getDepartmentDao()
	{
		return (DepartmentDao)baseDao;
	}
	
	public List<Department> getAll()
	{
		return getDepartmentDao().findList("from Department order by departmentId");
	}
	
	public PageData<Department> getAllByPage(int pageNum, Department entity)
	{
		return getDepartmentDao().findAllByPage(pageNum, entity);
	}
	
	public List<Department> getByFixedQuota()
	{
		return getDepartmentDao().findByFixedQuota();
	}
	
	public List<Department> getTotalScore(String deptType)
	{
		return getDepartmentDao().findTotalScore(deptType);
	}
	
	public List<Department> getTotalScore(String deptType, int num)
	{
		List<Department> depts = getDepartmentDao().findTotalScore(deptType);
		if(depts.size() > num)
		{
			return depts.subList(0, num);
		}
		else
		{
			return depts;
		}
	}
	
}