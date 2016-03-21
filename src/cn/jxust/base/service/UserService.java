package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.UserDao;
import cn.jxust.base.model.Area;
import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class UserService extends BaseService<User>
{
	@Resource
	DepartmentService departmentService;
	
	@Override
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public UserDao getUserDao()
	{
		return (UserDao)baseDao;
	}
	
	public User getUserByName(String name)
	{
		return getUserDao().findUserByName(name);
	}
	
	public User getUserByPhoneNumber(String userName)
	{
		return getUserDao().findUserByPhoneNumber(userName);
	}
	
	public PageData<User> getAllByPage(int pageNum, User entity, int type)
	{
		return getUserDao().findAllByPage(pageNum, entity, type);
	}
	
	public PageData<User> getAllByPage(int pageNum, User entity, Department department, int type)
	{
		return getUserDao().findAllByPage(pageNum, entity, department, type);
	}
	
	/**
	 * 修改用户密码，判断当前密码是否正确，再进行密码修改
	 */
	public boolean changePassword(User currentUser, String newPassword, String oldPassword)
	{
		if(currentUser.getPassword().equals(oldPassword))
		{
			currentUser.setPassword(newPassword);
			getUserDao().update(currentUser);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 通过用户单获取用户列表
	 * @param userType 用户类型
	 * @return
	 */
	public List<User> getUserByDepartment(Department department){
		return getUserDao().findUserByDepartment(department);
	}
	
	/**
	 * 初始化密码
	 * @param user
	 */
	public void initPassword(int userId){
		User user = getUserDao().find(userId);
		user.setPassword("111111");  //密码初始化为6个“1”。
		getUserDao().update(user);		
	}
	
	public void save(User user, String deptId, String RoleId)
	{
		getUserDao().save(user);
	}
	
	public List<User> findAll()
	{
		return getUserDao().findList("from User");
	}
	
	/**
	 * 生成以单位分类的管理员用户树,
	 * @param department
	 * @return
	 */
	public String createUserTree(List<Department> departments)
	{
		StringBuffer sb = new StringBuffer();
		//超级管理员登录
			for(Department dep: departments)
			{
				sb.append("<li><a href=\"base/user/depUserList/");
				sb.append(dep.getDepartmentId().toString());
				sb.append(".php\" target=\"ajax\" rel=\"jbsxBox\">");
				sb.append(dep.getDepartmentName());
				sb.append("</a></li>");
			}
		
		return sb.toString();
	}

	/**
	 * 以单位的方式在首页显示单位管理员和单位访客
	 * @param department
	 * @return
	 */
	public String createUserTree(Department department) {
		StringBuffer sb = new StringBuffer();
		if(null != department)  // 单位用户登录
		{
			sb.append("<li><a>");
			sb.append(department.getDepartmentName().toString());
			sb.append("</a><ul>");
			for (Area area: department.getAreas()) {
				sb.append("<li><a href=\"base/user/list/3.php\"");
				sb.append(" target=\"navTab\" rel=\"userList\">");
				sb.append(area.getAreaName());
				sb.append("</a></li>");
			}
			sb.append("<li><a href=\"base/deparea/list.php\"");
			sb.append(" target=\"navTab\" rel=\"areaList\">区域维护");
			sb.append("</a></li>");
			sb.append("</ul></li>");
		}
		else  //超级管理员登录
		{
			for(Department dep: departmentService.getAll())
			{
				sb.append("<li><a>");
				sb.append(dep.getDepartmentName().toString());
				sb.append("</a><ul>");
				sb.append("<li><a href=\"base/user/depUserList/");
				sb.append(dep.getDepartmentId().toString());
				sb.append(".php\" target=\"navTab\" rel=\"userList\">单位管理员维护");
				sb.append("</a></li>");
				sb.append("<li><a href=\"base/user/list/2.php\" target=\"navTab\" rel=\"userList\">单位访客维护");
				sb.append("</a></li>");
				sb.append("<li><a href=\"base/deparea/list.php\" target=\"navTab\" rel=\"areaList\">区域维护");
				sb.append("</a></li>");
				sb.append("</ul></li>");
			}
		}
		
		return sb.toString();
	}
	
}