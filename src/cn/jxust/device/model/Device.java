package cn.jxust.device.model;

import java.util.Set;

import cn.jxust.base.model.Area;
import cn.jxust.base.model.User;

import com.dabizi.point.model.PointInfo;

/**
 * 设备（控制器）
 * @author lt
 */
public class Device {
	private Integer deviceId; // 数据库表的主键
	private String deviceCode; // 设备编号 信息模块ID
	private String deviceName; //控制器名称
	private String deviceDesc; //设备描述
	private Integer proberCount; // 探测器安装数量
	private Integer proberAlarmCount = 0; // 探测器报警数量
	private Integer proberFaultCount = 0; // 探测器故障数量
	private Integer attachDeviceCount;  // 外控制器安装数量
	private Integer attachDeviceFaultCount = 0;  // 外控制器故障数量
	private String status = "<td style=\"color:#009900\">正常</td>";  // 控制器的状态,也即故障类型
	private boolean flag = false; // 是否建立电子地图结点
	private Integer deviceFalutFlag = 0;  // 控制器故障标记位.0：正常；1：发生故障
	private Integer proberFalutFlag = 0;  // 探测器故障标记位.0：正常；1：发生故障
	private Integer proberAlarmFlag = 0;  // 探测器报警标记位.0：正常；1：发生报警
	private Integer attachDeviceFaultFlag;  // 外控器状态标记位.0：正常；1：发生故障
	private Integer netFaultFlag = 0;  // 控制器网络故障标记.0：正常；1：发生故障
	
	private Set<User> deviceManagers; // 控制器管理员
	private Set<Prober> probers;
	private Set<AttachDevice> attachDevices;
	private PointInfo pointInfo;  // 电子地图中结点对应的控制器，一对一关系。
	private Area area;      	  // 区域和控制器一对多关系
	
	/**
	 * @return the deviceId
	 */
	public Integer getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return deviceCode;
	}
	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceDesc() {
		return deviceDesc;
	}
	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	public Set<Prober> getProbers() {
		return probers;
	}
	public void setProbers(Set<Prober> probers) {
		this.probers = probers;
	}
	public PointInfo getPointInfo() {
		return pointInfo;
	}
	public void setPointInfo(PointInfo pointInfo) {
		this.pointInfo = pointInfo;
	}
	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}
	/**
	 * @return the proberCount
	 */
	public Integer getProberCount() {
		return proberCount;
	}
	/**
	 * @param proberCount the proberCount to set
	 */
	public void setProberCount(Integer proberCount) {
		this.proberCount = proberCount;
	}
	/**
	 * @return the attachDeviceCount
	 */
	public Integer getAttachDeviceCount() {
		return attachDeviceCount;
	}
	/**
	 * @param attachDeviceCount the attachDeviceCount to set
	 */
	public void setAttachDeviceCount(Integer attachDeviceCount) {
		this.attachDeviceCount = attachDeviceCount;
	}
	/**
	 * @return the deviceManagers
	 */
	public Set<User> getDeviceManagers() {
		return deviceManagers;
	}
	/**
	 * @param deviceManagers the deviceManagers to set
	 */
	public void setDeviceManagers(Set<User> deviceManagers) {
		this.deviceManagers = deviceManagers;
	}
	/**
	 * @return the proberAlarmCount
	 */
	public Integer getProberAlarmCount() {
		return proberAlarmCount;
	}
	/**
	 * @param proberAlarmCount the proberAlarmCount to set
	 */
	public void setProberAlarmCount(Integer proberAlarmCount) {
		this.proberAlarmCount = proberAlarmCount;
	}
	/**
	 * @return the proberFaultCount
	 */
	public Integer getProberFaultCount() {
		return proberFaultCount;
	}
	/**
	 * @param proberFaultCount the proberFaultCount to set
	 */
	public void setProberFaultCount(Integer proberFaultCount) {
		this.proberFaultCount = proberFaultCount;
	}
	/**
	 * @return the attachDeviceFaultCount
	 */
	public Integer getAttachDeviceFaultCount() {
		return attachDeviceFaultCount;
	}

	/**
	 * @param attachDeviceFaultCount the attachDeviceFaultCount to set
	 */
	public void setAttachDeviceFaultCount(Integer attachDeviceFaultCount) {
		this.attachDeviceFaultCount = attachDeviceFaultCount;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the attachDevices
	 */
	public Set<AttachDevice> getAttachDevices() {
		return attachDevices;
	}
	/**
	 * @param attachDevices the attachDevices to set
	 */
	public void setAttachDevices(Set<AttachDevice> attachDevices) {
		this.attachDevices = attachDevices;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the proberFalutFlag
	 */
	public Integer getProberFalutFlag() {
		return proberFalutFlag;
	}
	/**
	 * @param proberFalutFlag the proberFalutFlag to set
	 */
	public void setProberFalutFlag(Integer proberFalutFlag) {
		this.proberFalutFlag = proberFalutFlag;
	}
	/**
	 * @return the proberAlarmFlag
	 */
	public Integer getProberAlarmFlag() {
		return proberAlarmFlag;
	}
	/**
	 * @param proberAlarmFlag the proberAlarmFlag to set
	 */
	public void setProberAlarmFlag(Integer proberAlarmFlag) {
		this.proberAlarmFlag = proberAlarmFlag;
	}
	/**
	 * @return the attachDeviceStatusFlag
	 */
	public Integer getAttachDeviceFaultFlag() {
		return attachDeviceFaultFlag;
	}
	/**
	 * @param attachDeviceStatusFlag the attachDeviceStatusFlag to set
	 */
	public void setAttachDeviceFaultFlag(Integer attachDeviceFaultFlag) {
		this.attachDeviceFaultFlag = attachDeviceFaultFlag;
	}
	/**
	 * @return the deviceFalutFlag
	 */
	public Integer getDeviceFalutFlag() {
		return deviceFalutFlag;
	}
	/**
	 * @param deviceFalutFlag the deviceFalutFlag to set
	 */
	public void setDeviceFalutFlag(Integer deviceFalutFlag) {
		this.deviceFalutFlag = deviceFalutFlag;
	}
	/**
	 * @return the netFaultFlag
	 */
	public Integer getNetFaultFlag() {
		return netFaultFlag;
	}
	/**
	 * @param netFaultFlag the netFaultFlag to set
	 */
	public void setNetFaultFlag(Integer netFaultFlag) {
		this.netFaultFlag = netFaultFlag;
	}
		
}
