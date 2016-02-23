package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.AlarmParameterDao;
import cn.jxust.device.model.AlarmParameter;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class AlarmParameterService extends BaseService<AlarmParameter>
{
	@Override
	@Resource(name="alarmParameterDao")
	public void setBaseDao(BaseDao<AlarmParameter> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public AlarmParameterDao getAlarmParameterDao()
	{
		return (AlarmParameterDao)baseDao;
	}
	
	public PageData<AlarmParameter> getAll(PageData<AlarmParameter> pageData)
	{
		return getAlarmParameterDao().getAll(pageData);
	}
	
	public void save(AlarmParameter alarmParameter)
	{
		getAlarmParameterDao().save(alarmParameter);
	}
	
	public void update(AlarmParameter alarmParameter)
	{
		getAlarmParameterDao().update(alarmParameter);
	}
	
	public void delete(String[] id)
	{
		AlarmParameter news = null;
		for(String item : id)
		{
			news = find(item);
			getAlarmParameterDao().delete(news);
		}
	}
	
	/**
	 * 寻找所得单位对应的值
	 * @return
	 */
	public AlarmParameter findUnitVal(String unit){
			return getAlarmParameterDao().findUnitVal(unit);
	}
	
}
