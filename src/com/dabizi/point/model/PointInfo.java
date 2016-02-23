package com.dabizi.point.model;

import cn.jxust.device.model.Device;

public class PointInfo {
	public static final int STATUS_OK = 0; // 控制器故障，地图对应点红色标注
	public static final int STATUS_ERROR = 1; // 控制器正常，地图对应点绿色标注
	
	private Integer id;
	private String pointStatus; // 结点状态，也即探测器状态
	
	private String longitude; // 经度
	private String latitude;  // 纬度
	private Device device;    // 一个地图结点对一个控制器，一对一关系
	
	private Integer status; // 对应控制器的状态
	private String statusColor; // 对应控制器的状态显示颜色
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointStatus() {
		return pointStatus;
	}
	public void setPointStatus(String pointStatus) {
		this.pointStatus = pointStatus;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the statusColor
	 */
	public String getStatusColor() {
		return statusColor;
	}
	/**
	 * @param statusColor the statusColor to set
	 */
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
