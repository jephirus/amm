/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.jxust.base.model;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * @author Administrator
 */
public class Authority implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8913155145896302794L;
    private int authId; // ID
    private String authority;
    private String authName; // 权限名称
    private String authType; // 类别（如：URL、METHOD,MENU）
    private String authDesc; // 描述
    private String protectedRes; // 受保护资源的路径
    private boolean enabled = true;
    private List<Role> roles; // 某资源对应的角色，（资源和角色两者为多对多关系）
    private int ordNum;      //权限序号
    private int pId;         //父ID

    public int getAuthId() {
	return authId;
    }

    public void setAuthId(int authId) {
	this.authId = authId;
    }

    public String getAuthority() {
	return authority;
    }

    public void setAuthority(String authority) {
	this.authority = authority;
    }

    public String getAuthName() {
	return authName;
    }

    public void setAuthName(String authName) {
	this.authName = authName;
    }

    public String getAuthType() {
	return authType;
    }

    public void setAuthType(String authType) {
	this.authType = authType;
    }

    public String getProtectedRes() {
	return protectedRes;
    }

    public void setProtectedRes(String protectedRes) {
	this.protectedRes = protectedRes;
    }

	/**
	 * @return the authDesc
	 */
	public String getAuthDesc() {
		return authDesc;
	}

	/**
	 * @param authDesc the authDesc to set
	 */
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the ordNum
	 */
	public int getOrdNum() {
		return ordNum;
	}

	/**
	 * @param ordNum the ordNum to set
	 */
	public void setOrdNum(int ordNum) {
		this.ordNum = ordNum;
	}

	/**
	 * @return the pId
	 */
	public int getpId() {
		return pId;
	}

	/**
	 * @param pId the pId to set
	 */
	public void setpId(int pId) {
		this.pId = pId;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
