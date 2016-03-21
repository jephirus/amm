package cn.jxust.sms.model;

import java.io.Serializable;

import com.dabizi.amm.uegateSoapInterfaceAxis.CallUtils;

/**
 * 短信
 * @author lt
 */
public class SMS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String deviceId; // 设备ID号
	private String phonoNumbers = CallUtils.defaultNumber; // 短信绑定的电话号码，多个电话号码之间用，隔开
	private String content = CallUtils.defaultContent; // 短信内容
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPhonoNumbers() {
		return phonoNumbers;
	}
	public void setPhonoNumbers(String phonoNumbers) {
		this.phonoNumbers = phonoNumbers;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
