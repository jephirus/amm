/**
 * 
 */
package cn.jxust.base.model;

import java.util.Set;

/**
 * @author Administrator
 * 单位部门表
 */
public class Department {
    
    private Integer departmentId;
    private String departmentName;
    private String phoneNumber;  //电话号码
    private String address;  //单位地址
    private String departmentDesc;  //单位描述
    private Set<User> users;        // 一个单位可以多个管理员及游客
    private Set<Area> areas;		// 一个单位可以有多个区域

    public Set<Area> getAreas() {
		return areas;
	}
	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	/**
     * @return the departmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }
    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    /**
     * @return the departmentDesc
     */
    public String getDepartmentDesc() {
        return departmentDesc;
    }
    /**
     * @param departmentDesc the departmentDesc to set
     */
    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }
	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
