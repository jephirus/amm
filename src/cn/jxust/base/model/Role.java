package cn.jxust.base.model;

import java.io.Serializable;
import java.util.List;


public class Role implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色授权
	 */
	private Integer roleId; // 角色id
	private String roleName; // 角色名称
	private String roleDesc; // 角色描述
	private List<Authority> resources; // 角色能访问的资源

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc()
	{
		return roleDesc;
	}

	/**
	 * @param roleDesc
	 *            the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc)
	{
		this.roleDesc = roleDesc;
	}

	/**
	 * @return the resources
	 */
	public List<Authority> getResources()
	{
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(List<Authority> resources)
	{
		this.resources = resources;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
