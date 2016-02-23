package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.RoleDao;
import cn.jxust.base.model.Role;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class RoleService extends BaseService<Role>
{
	@Override
	@Resource(name="roleDao")
	public void setBaseDao(BaseDao<Role> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public RoleDao getRoleDao()
	{
		return (RoleDao)baseDao;
	}
	
	public List<Role> findAll()
	{
		return getRoleDao().findAll();
	}
	
	public List<Role> getRoleByName(String userName)
	{
		if(userName.equals("admin")){
			return getRoleDao().findRoleByAdmin();
		}
		else
		{
			return getRoleDao().findRoleByDepAdmin();
		}
	}
}
