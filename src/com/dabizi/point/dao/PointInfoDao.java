package com.dabizi.point.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dabizi.point.model.PointInfo;

import cn.jxust.base.model.User;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class PointInfoDao extends BaseDao<PointInfo> {
	
	public List<PointInfo> findAll()
	{
		return findList("from PointInfo");
	}

	public List<PointInfo> findUserPointInfo(User user){
		return findList("from PointInfo where user=?", user);
	}
	public PointInfo findPIByLL(String longitude, String latitude)
	{
		return find("from PointInfo where longitude =? and latitude =?", longitude, latitude);
	}

}
