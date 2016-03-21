package cn.jxust.device.dao;

import org.springframework.stereotype.Repository;

import cn.jxust.device.model.AlarmParameter;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class AlarmParameterDao extends BaseDao<AlarmParameter>{
	
	public PageData<AlarmParameter> getAll(PageData<AlarmParameter> pageData)
	{
		return findPage(pageData, "from AlarmParameter");
	}
	
	/**
	 * 寻找所得单位对应的值
	 * @return
	 */
	public AlarmParameter findUnitVal(String unit) {
		return findCache("from AlarmParameter where unit='" + unit + "'");
	}

}