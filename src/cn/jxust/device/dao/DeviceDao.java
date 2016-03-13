package cn.jxust.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.device.model.Device;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class DeviceDao extends BaseDao<Device>
{

	/**
	 * 通过设备id(不是主键)获取设备
	 * @param deviceId 设备id
	 * @return
	 */
	public Device findByDeviceId(Integer deviceId) {
		return find("from Device d where d.deviceId = "+deviceId);
	}

	public Device findByDeviceCode(String deviceCode) {
		return find("from Device where deviceCode=?",deviceCode);
	}

	/**
	 * 查找单位的所有控制器
	 * @param department
	 * @return
	 */
	public List<Device> findAll(Department department)
	{
		return findList("from Device d where d.area.department=? and flag!=false", department);
	}

	/**
	 * 查找区域的所有控制器
	 * @param area
	 * @return
	 */
	public List<Device> findAll(Area area)
	{
		return findList("from Device d where d.area=? and flag=false", area);
	}
	
	/**
	 * 以分页方式查找区域的所有控制器
	 * @param pageData
	 * @param area
	 * @return
	 */
	public PageData<Device> findAll(PageData<Device> pageData, Area area)
	{
		return findPage(pageData, "from Device d where d.area=?", area);
	}
}
