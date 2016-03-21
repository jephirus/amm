package com.dabizi.point.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.User;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

import com.dabizi.point.dao.PointInfoDao;
import com.dabizi.point.model.PointInfo;

@Service
@Transactional
public class PointInfoService extends BaseService<PointInfo> {

	@Override
	@Resource(name="pointInfoDao")
	public void setBaseDao(BaseDao<PointInfo> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 以分页形式查询所有设备结点信息。
	 * @param pageData
	 * @return
	 */
	public PageData<PointInfo> getAll(PageData<PointInfo> pageData)
	{
		return getPointInfoDao().findPage(pageData, "from PointInfo");
	}

	public List<PointInfo> findUserPointInfo(User user){
		return getPointInfoDao().findUserPointInfo(user);
	}
	/**
	 * 以分页形式查询所有设备结点信息。
	 * @param pageData
	 * @return
	 */
	public List<PointInfo> getAll()
	{
		return getPointInfoDao().findAll();
	}
	
	public PointInfoDao getPointInfoDao(){
		return (PointInfoDao)baseDao;
	}
	
	public void save(PointInfo pointInfo){
		getPointInfoDao().save(pointInfo);
	}
	
	public void update(PointInfo pointInfo)
	{
		getPointInfoDao().update(pointInfo);
	}
	
	public void delete(String[] pointInfoIds)
	{
		getPointInfoDao().delete(pointInfoIds);
	}

	public List<PointInfo> findAll() {
		return getPointInfoDao().findList("from PointInfo");
	}

	/**
	 * 根据经纬度获取控制结点信息
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public PointInfo findPIByLL(String longitude, String latitude){
		return getPointInfoDao().findPIByLL(longitude, latitude);
	}

}
