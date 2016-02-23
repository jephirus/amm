package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class UserDao extends BaseDao<User> 
{
	/**
	 * 通过用户名获取用户对象
	 * @param name
	 * @return
	 */
	public User findUserByName(String name)
	{
		return find("from User u where u.userName = ?", name);
	}

	/**
	 * 通过用户的电话号获取用户，在该系统中用于登录。
	 * @param phoneNumber
	 * @return
	 */
	public User findUserByPhoneNumber(String userName){
		return find("from User where userName = ?", userName);
	}
	
	/**
	 * 通过用户单位获取用户列表
	 * @param userType
	 * @return
	 */
	public List<User> findUserByDepartment(Department department){
		return findList("from User where userDep = ?", department);
	}
	
	public PageData<User> findAllByPage(int pageNum, User entity, int type)
	{
		PageData<User> pageData = new PageData<User>(pageNum);
		// 在用户列表中不显示超级管理员admin。
		pageData = this.findPage(pageData, "from User u where u.userName <> 'admin' and u.type=?", type); 
		return pageData;
	}
	
	public PageData<User> findAllByPage(int pageNum, User entity, Department department, int type)
	{
		PageData<User> pageData = new PageData<User>(pageNum);
		// 在用户列表中不显示超级管理员admin。
		pageData = this.findPage(pageData, "from User u where u.userName <> 'admin' and u.userDep=? and u.type=?", department, type); 
		return pageData;
	}
}
