package cn.jxust.base.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.AuthorityDao;
import cn.jxust.base.model.Authority;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class AuthorityService extends BaseService<Authority>
{
	@Override
	@Resource(name="authorityDao")
	public void setBaseDao(BaseDao<Authority> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public AuthorityDao getAuthorityDao()
	{
		return (AuthorityDao)baseDao;
	}
}
