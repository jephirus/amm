package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Role;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class RoleDao extends BaseDao<Role>
{

	public List<Role> findAll(){
		// 前台界不显示超级用户角色
		return findList("from Role r where r.roleName <> 'SYS_ADMIN'");
	}
	/**
	 * 超级管理员可获取的角色
	 * @return
	 */
	public List<Role> findRoleByAdmin(){
		// 这里单位用户通过建立单位来创建，因此不检索单位用户角色。
		return findList("from Role r where r.roleName <> 'SYS_ADMIN' and r.roleName <> 'SYS_DEPT_ADMIN' and r.roleName <> 'SYS_DEPT_MANAGER'");
	}

	/**
	 * 单位机构用户可获取的角色
	 * @return
	 */
	public List<Role> findRoleByDepAdmin(){
		return findList("from Role r where r.roleName = 'SYS_DEPT_MANAGER'");
	}
}
