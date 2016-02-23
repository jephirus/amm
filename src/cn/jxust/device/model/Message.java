package cn.jxust.device.model;

import java.io.Serializable;

import cn.jxust.sms.model.SMS;

/**
 * @author lt 协议解析得到的相关信息
 */
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String startChar; // 启动符号
	private int serialNum; // 流水号
	private int versionNum; // 版本号
	private String timeLaber; // 时间标签
	private String infoID; // 信息模块ID
	private int dataLength; // 数据长度
	private int command; // 命令
	private int checkSum; // 检验和
	//private int dataUnit; // 数据单元
	private String endChar; // 结束符号
	//private String concentration; // 包含实际浓度等信息，使用时需要进行一些处理，将比如将关心的信息截取出来

	private String unitStatus;
	private SMS sms; // 一个设备对应一个短信，一个短信可以有好多的电话号码
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartChar() {
		return startChar;
	}

	public void setStartChar(String startChar) {
		this.startChar = startChar;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getTimeLaber() {
		return timeLaber;
	}

	public void setTimeLaber(String timeLaber) {
		this.timeLaber = timeLaber;
	}

	public String getInfoID() {
		return infoID;
	}

	public void setInfoID(String infoID) {
		this.infoID = infoID;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}

	/*public int getDataUnit() {
		return dataUnit;
	}

	public void setDataUnit(int dataUnit) {
		this.dataUnit = dataUnit;
	}
*/
	public String getEndChar() {
		return endChar;
	}

	public void setEndChar(String endChar) {
		this.endChar = endChar;
	}

/*	public String getConcentration() {
		return concentration;
	}

	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}*/
	
	public String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
	}

	@Override
	public String toString() {
		return "MessageInfo [startChar=" + startChar + ", serialNum="
				+ serialNum + ", versionNum=" + versionNum + ", timeLaber="
				+ timeLaber + ", infoID=" + infoID + ", dataLength="
				+ dataLength + ", command=" + command + ", checkSum="
				+ checkSum + ", endChar=" + endChar
				+ "]";
	}

	public SMS getSms() {
		return sms;
	}

	public void setSms(SMS sms) {
		this.sms = sms;
	}
}
