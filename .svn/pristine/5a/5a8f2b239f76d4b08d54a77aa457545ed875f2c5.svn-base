package cn.jxust.base.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import cn.jxust.device.model.Device;

/**
 * 用户类，其中单位/机构用户和其管理员用户使用手机号做为登录名用。
 * @author Jephirus
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = -6402654083259438236L;
    private Integer userId;
    private String userName = null;  //登录名
    private String phoneNumber = null;
    private String password = null;
    private Department userDep = null;
    private boolean enabled = true;
    private String userDesc = null;
    private String realName;   // 真实姓名
    private Set<Device> device;     // 用户所管理控制器
	private List<Role> userRole; 	     //用户对应的角色
	private int type;     // 超级游客，类型为1；如果是单位游客，类型为2；如果是单位管理员，类型为3
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Department getUserDep() {
		return userDep;
	}
	public void setUserDep(Department userDep) {
		this.userDep = userDep;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public List<Role> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<Role> userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the device
	 */
	public Set<Device> getDevice() {
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(Set<Device> device) {
		this.device = device;
	}

}