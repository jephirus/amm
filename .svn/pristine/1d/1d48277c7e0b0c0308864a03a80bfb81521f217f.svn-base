package cn.jxust.device.model;

public class ExplorerMessage extends Message{

	/**
	 * @author: Laiy
	 * @description:消防设施部件（探测器）运行状态；分两种情况：探测器和外控器
	 * @date:2015年10月13日
	 */
	private static final long serialVersionUID = 1L;

	private int dataUnitBsm;//信息数据单元标识码， = 2；
	private int dataCount;//信息数据个数

	private int dataType;//系统类型标识码， = 1.
	private String sysAddress;//系统地址，即一个信息模块ID下的控制器地址，通过信息模块ID+系统地址可标识一个控制器。
	private int deviceType;//部件类型，探测器 = 11；外控器 = 86
	private String deviceAddress;//部件地址
	private String deviceStatus;//部件状态
	
	public int getDataUnitBsm() {
		return dataUnitBsm;
	}
	public void setDataUnitBsm(int dataUnitBsm) {
		this.dataUnitBsm = dataUnitBsm;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public String getSysAddress() {
		return sysAddress;
	}
	public void setSysAddress(String sysAddress) {
		this.sysAddress = sysAddress;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
}
