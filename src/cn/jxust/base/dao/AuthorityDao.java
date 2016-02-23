package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Authority;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class AuthorityDao extends BaseDao<Authority>
{
	public List<Authority> getAuthorities(){
		return findList("from Authority");
	}
}
