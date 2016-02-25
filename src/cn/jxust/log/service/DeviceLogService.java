package cn.jxust.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Department;
import cn.jxust.log.dao.DeviceLogDao;
import cn.jxust.log.model.DeviceLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class DeviceLogService extends BaseService<DeviceLog>
{
	public PageData<DeviceLog> getAllDeviceLog(PageData<DeviceLog> pageData)
	{
		return getDeviceLogDao().findPage(pageData);
	}

	/**
	 * 带单位查询
	 * @param pageData
	 * @param department
	 * @return
	 */
	public PageData<DeviceLog> getAllDeviceLog(PageData<DeviceLog> pageData, Department department)
	{
		return getDeviceLogDao().findPage(pageData, department);
	}

	@Override
	@Resource(name="deviceLogDao")
	public void setBaseDao(BaseDao<DeviceLog> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public DeviceLogDao getDeviceLogDao()
	{
		return (DeviceLogDao)baseDao;
	}

	public PageData<DeviceLog> getDeviceLogByQuery(String deviceName,
			String proberLocation, String beginDate, String endDate, int pageNum) {
		return null;
	}
	

}
