package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.AreaDao;
import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
@Service
@Transactional
public class AreaService extends BaseService<Area>{

	
	@Override
	@Resource(name="areaDao")
	public void setBaseDao(BaseDao<Area> baseDao) {
		this.baseDao=baseDao;
	}
	
	public AreaDao getAreaDao(){
		return (AreaDao) baseDao;
	}
	
	public PageData<Area> getAllByPage(int pageNum,Area entity){
		return  getAreaDao().findAllByPage(pageNum, entity);
	}

	public PageData<Area> getAllByPage(int pageNum,Area entity, Department department){
		return  getAreaDao().findAllByPage(pageNum, entity, department);
	}
	
	public List<Area> getAll()
	{
		return getAreaDao().findAll();
	}

}
