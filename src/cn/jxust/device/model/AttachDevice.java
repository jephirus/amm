/**
 * 
 */
package cn.jxust.device.model;

/**
 * 探测器，和控制器是多对一，即一个控制器对应多个探测器
 * @author Jephirus
 *
 */
public class AttachDevice {

	private Integer attachDeviceId;   // 外控制器ID
	private String attachDeviceNum;   // 外控制器编号
	private Device device;      // 外控制器所对应的控制器
	private String location; // 探测器安装地点
	private String currentStatus = "<td style=\"color:#009900\">正常</td>";       // 外控器状态
	private String alarmTime = "<td style=\"color:#009900\"></td>";// 报警时音
	private String installDate;   // 安装时间

	/**
	 * @return the attachDeviceId
	 */
	public Integer getAttachDeviceId() {
		return attachDeviceId;
	}
	/**
	 * @param attachDeviceId the attachDeviceId to set
	 */
	public void setAttachDeviceId(Integer attachDeviceId) {
		this.attachDeviceId = attachDeviceId;
	}
	/**
	 * @return the attachDeviceNum
	 */
	public String getAttachDeviceNum() {
		return attachDeviceNum;
	}
	/**
	 * @param attachDeviceNum the attachDeviceNum to set
	 */
	public void setAttachDeviceNum(String attachDeviceNum) {
		this.attachDeviceNum = attachDeviceNum;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}
	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	/**
	 * @return the alarmTime
	 */
	public String getAlarmTime() {
		return alarmTime;
	}
	/**
	 * @param alarmTime the alarmTime to set
	 */
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	/**
	 * @return the installDate
	 */
	public String getInstallDate() {
		return installDate;
	}
	/**
	 * @param installDate the installDate to set
	 */
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	
}
