package cn.jxust.device.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dabizi.amm.receiver.DeviceTimer;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.device.dao.DeviceDao;
import cn.jxust.device.model.AttachDevice;
import cn.jxust.device.model.Device;
import cn.jxust.device.model.Prober;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.SpringContextUtils;

@Service
@Transactional
public class DeviceService extends BaseService<Device>
{
	
	public DeviceDao getDeviceDao(){
		return (DeviceDao) baseDao;
	}

	@Override
	@Resource(name="deviceDao")
	public void setBaseDao(BaseDao<Device> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	@Resource
	ProberService proberService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	AttachDeviceService attachDeviceService;
	
	public PageData<Device> getAll(PageData<Device> pageData, Area area)
	{
		return getDeviceDao().findAll(pageData, area);
	}
	
	public List<Device> getAll()
	{
		return getDeviceDao().findList("from Device");
	}
	
	/**
	 * 获取某单位所有的控制器信息。
	 * @param department
	 * @return
	 */
	public List<Device> getAll(Department department)
	{
		return getDeviceDao().findAll(department);
	}
	
	public void save(Integer proberCount, Integer attachDeviceCount, Device device)
	{
		Prober p;
		AttachDevice ad;
		if (device.getDeviceId() == null) {  // 新增对象
			getDeviceDao().save(device);   // 先保存控制器
			
			for (int i = 0; i < proberCount; i++) {
				p = new Prober();
				p.setProberNum(String.format("%08d", i + 1));
				p.setLocation(String.format("%08d", i + 1));
				p.setProberRange("100.0");
				p.setLowThickness("20.0");
				p.setHighThickness("50.0");
				p.setInstallDate(new Date().toString());
				p.setDevice(device);
				proberService.save(p);  // 再保存控测器
				getDeviceDao().update(device);
			}
			// 最后保存外控器
			for (int i = 0; i < attachDeviceCount; i++) {
				ad = new AttachDevice();
				ad.setAttachDeviceNum(String.format("%08d", i + 1)); // 外控器编号
				ad.setLocation(String.format("%08d", i + 1));
				ad.setInstallDate(new Date().toString());
				ad.setDevice(device);
				attachDeviceService.save(ad);  // 再保存控测器
			}
		}
		else{   // 更新对象
			Device serialDevice = getDeviceDao().find(device.getDeviceId());
			int pCount = proberCount + serialDevice.getProberCount();
			for (int i = serialDevice.getProberCount(); i < pCount; i++)
			{
				p = new Prober();
				p.setProberNum(String.format("%08d", i + 1));
				p.setLocation(String.format("%08d", i + 1));
				p.setProberRange("100.0");
				p.setLowThickness("20.0");
				p.setHighThickness("50.0");
				p.setInstallDate(new Date().toString());
				p.setDevice(serialDevice);
				serialDevice.setProberCount(pCount);
				proberService.save(p);  // 再保存控测器
			}
			int aCount = attachDeviceCount + serialDevice.getAttachDeviceCount();
			// 最后保存外控器
			for (int i = serialDevice.getAttachDeviceCount(); i < aCount; i++)
			{
				ad = new AttachDevice();
				ad.setAttachDeviceNum(String.format("%08d", i + 1));
				ad.setLocation(String.format("%08d", i + 1));
				ad.setInstallDate(new Date().toString());
				ad.setDevice(serialDevice);
				serialDevice.setAttachDeviceCount(aCount);
				attachDeviceService.save(ad);  // 再保存控测器
			}

			getDeviceDao().update(serialDevice);
		}
	}

	public Device findByDeviceCode(String deviceCode) {
		
		return getDeviceDao().findByDeviceCode(deviceCode);
	}

	/**
	 * 生成控制器监控树
	 * @param department
	 * @return
	 */
	public String createMonitorTree(Department department)
	{
		StringBuffer sb = new StringBuffer();
		if(null != department)  // 单位用户登录
		{
			sb.append("<li><a>");
			sb.append(department.getDepartmentName().toString());
			sb.append("</a><ul>");
			for (Area area: department.getAreas()) {
				sb.append("<li><a href=\"device/monitorList/");
				sb.append(area.getAreaId().toString());
				sb.append(".php\" target=\"navTab\" rel=\"deviceList\">");
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
				for (Area area: dep.getAreas()) {
					sb.append("<li><a href=\"device/monitorList/");
					sb.append(area.getAreaId().toString());
					sb.append(".php\" target=\"navTab\" rel=\"deviceList\">");
					sb.append(area.getAreaName());
					sb.append("</a></li>");
				}
				sb.append("</ul></li>");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成控制器信息列表
	 * @param department
	 * @return
	 */
	public String createDeviceTree(Department department)
	{
		StringBuffer sb = new StringBuffer();
		if(null != department)  // 单位用户登录
		{
			sb.append("<li><a>");
			sb.append(department.getDepartmentName().toString());
			sb.append("</a><ul>");
			for (Area area: department.getAreas()) {
				sb.append("<li><a href=\"device/deviceList/");
				sb.append(area.getAreaId().toString());
				sb.append(".php\" target=\"navTab\" rel=\"deviceList\">");
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
				for (Area area: dep.getAreas()) {
					sb.append("<li><a href=\"device/deviceList/");
					sb.append(area.getAreaId().toString());
					sb.append(".php\" target=\"navTab\" rel=\"deviceList\">");
					sb.append(area.getAreaName());
					sb.append("</a></li>");
				}
				sb.append("</ul></li>");
			}
		}
		
		return sb.toString();
	}

	public Device getDeviceByCode(String deviceCode) {
		return getDeviceDao().findByDeviceCode(deviceCode);
	}
	
}
