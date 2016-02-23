/**
 * 
 */
package cn.jxust.log.model;

/**
 * 探测器日志
 * 
 * @author Jephirus
 *
 */
public class AttachDeviceLog {

	private String deviceName; // 控制器名称
	private String areaName; // 区域
	private String departmentName; // 所属单位
	private String controllerStatus; // 故障状态
	private String timeLaber; // 故障时间
	private String address; // 探测器地址

	public AttachDeviceLog(String deviceName, String areaName, String departmentName,
			String controllerStatus, String timeLaber, String address) {
		this.deviceName = deviceName;
		this.areaName = areaName;
		this.departmentName = departmentName;
		this.controllerStatus = controllerStatus;
		this.timeLaber = timeLaber;
		this.address = address;
	}

	public AttachDeviceLog() {

	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName
	 *            the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the controllerStatus
	 */
	public String getControllerStatus() {
		return controllerStatus;
	}

	/**
	 * @param controllerStatus
	 *            the controllerStatus to set
	 */
	public void setControllerStatus(String controllerStatus) {
		this.controllerStatus = controllerStatus;
	}

	/**
	 * @return the timeLaber
	 */
	public String getTimeLaber() {
		return timeLaber;
	}

	/**
	 * @param timeLaber
	 *            the timeLaber to set
	 */
	public void setTimeLaber(String timeLaber) {
		this.timeLaber = timeLaber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
