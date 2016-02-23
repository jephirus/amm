package cn.jxust.base.model;

import java.io.Serializable;
import java.util.Set;

import cn.jxust.device.model.Device;

public class Area implements Serializable{

	/**  
	 * @Description:  区域javabean
	 * @author:laiy 
	 * @date 2015年10月18日  下午3:43:48 
	 *  
	 */ 
	private static final long serialVersionUID = 1L;

	private Integer areaId;//区域id
	private String areaName;//区域名称
	private Department department;//单位和区域一对多
	
	private Set<Device> devices;  // 区域和控制器一对多
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * @return the devices
	 */
	public Set<Device> getDevices() {
		return devices;
	}
	/**
	 * @param devices the devices to set
	 */
	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}
	
	
}
