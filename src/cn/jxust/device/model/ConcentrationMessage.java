package cn.jxust.device.model;

public class ConcentrationMessage extends Message {

	/**
	 * @author: Laiy
	 * @description:消防设施部件模拟量值探测器，类型标识符= 7；部件类型为11.，有浓度的
	 * @date:2015年10月13日
	 */
	private static final long serialVersionUID = 1L;

	private int dataUnitBsm;		// 数据单元标识码，即类型标识符= 7
	private int dataCount;			// 信息数据个数

	private int dataType;			// 系统类型标识码，=1
	private String sysAddress;		// 系统地址，控制器地址
	private int deviceType;			// 部件类型标识符，= 11
	private String deviceAddress;	// 部件地址，即探测器地址
	private String simulation;		// 模拟量值，即浓度
	private String status;			// 状态值

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

	public String getSimulation() {
		return simulation;
	}

	public void setSimulation(String simulation) {
		this.simulation = simulation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
