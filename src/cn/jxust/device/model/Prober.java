/**
 * 
 */
package cn.jxust.device.model;

/**
 * 探测器，和控制器是多对一，即一个控制器对应多个探测器
 * @author Jephirus
 *
 */
public class Prober {

	private Integer proberId;   // 探测器ID
	private String proberNum;   // 探测器编号
	private Device device;      // 探测器所对应的控制器
	private String location; // 探测器地址
	private String proberRange;       // 量程
	private String lowThickness;// 低端浓度
	private String highThickness; // 高端浓度
	private String installDate;   // 安装时间
	private String alarmValue = "10";   // 预警值
	
	///////////-- 通过对接收到消息进行分析，如需报警，将消息中的相关信息写到该探测器中。
	private String currentStatus ="<td style=\"color:#009900\">正常</td>";  // 当前状态
	private String alarmTime = "<td style=\"color:#009900\"></td>";       // 报警时间
	private String currentThickness;  // 当前浓度
	

	public Integer getProberId() {
		return proberId;
	}
	public void setProberId(Integer proberId) {
		this.proberId = proberId;
	}

	public String getProberNum() {
		return proberNum;
	}
	public void setProberNum(String proberNum) {
		this.proberNum = proberNum;
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
	 * @return the proberRange
	 */
	public String getProberRange() {
		return proberRange;
	}
	/**
	 * @param proberRange the proberRange to set
	 */
	public void setProberRange(String proberRange) {
		this.proberRange = proberRange;
	}
	/**
	 * @return the lowThickness
	 */
	public String getLowThickness() {
		return lowThickness;
	}
	/**
	 * @param lowThickness the lowThickness to set
	 */
	public void setLowThickness(String lowThickness) {
		this.lowThickness = lowThickness;
	}
	/**
	 * @return the highThickness
	 */
	public String getHighThickness() {
		return highThickness;
	}
	/**
	 * @param highThickness the highThickness to set
	 */
	public void setHighThickness(String highThickness) {
		this.highThickness = highThickness;
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
	/**
	 * @return the alarmValue
	 */
	public String getAlarmValue() {
		return alarmValue;
	}
	/**
	 * @param alarmValue the alarmValue to set
	 */
	public void setAlarmValue(String alarmValue) {
		this.alarmValue = alarmValue;
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
	 * @return the currentThickness
	 */
	public String getCurrentThickness() {
		return currentThickness;
	}
	/**
	 * @param currentThickness the currentThickness to set
	 */
	public void setCurrentThickness(String currentThickness) {
		this.currentThickness = currentThickness;
	}
	
}
