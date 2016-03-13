package cn.jxust.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.log.dao.DeviceLogDao;
import cn.jxust.log.model.DeviceLog;
import cn.jxust.log.model.ProberLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class DeviceLogService extends BaseService<DeviceLog>
{
	@Resource
	DepartmentService departmentService;
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

	public PageData<DeviceLog> getDeviceLogByQuery(PageData<DeviceLog> pageData, Integer deviceCode, String proberNum, String beginDate, String endDate, int pageNum, Department department) {
		PageData<DeviceLog> pd = getDeviceLogDao().getDeviceLogByQuery(pageData, deviceCode, proberNum, beginDate, endDate, pageNum, department);
		return pd;
	}

	public String createDeviceLogTree(Department department) {
		StringBuffer sb = new StringBuffer();
		if(null != department)  // 单位用户登录
		{
			sb.append("<li><a>");
			sb.append(department.getDepartmentName().toString());
			sb.append("</a><ul>");
			for (Area area: department.getAreas()) {
				sb.append("<li><a href=\"log/device/list.php\"");
				sb.append(" target=\"navTab\" rel=\"deviceList\">");
				sb.append(area.getAreaName());
				sb.append("</a></li>");
			}
			sb.append("</ul></li>");
		}
		else  //超级管理员登录
		{
			for(Department dep: departmentService.getAll())
			{
				sb.append("<li><a>");
				sb.append(dep.getDepartmentName().toString());
				sb.append("</a><ul>");
				sb.append("<li><a href=\"log/device/list/");
				sb.append(dep.getDepartmentId().toString());
				sb.append(".php\" target=\"navTab\" rel=\"deviceList\">日志查询");
				sb.append("</a></li>");
				sb.append("</ul></li>");
			}
		}
		
		return sb.toString();
	}

	public PageData<ProberLog> getProberLogByQuery(PageData<ProberLog> pageData, Integer deviceCode,
			String proberNum, String beginDate, String endDate, int pageNum, Department department) {
		PageData<ProberLog> pd = getDeviceLogDao().getProberLogByQuery(pageData, deviceCode, proberNum, beginDate, endDate, pageNum, department);
		return pd;
	}
	

}
