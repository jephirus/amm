package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.Config;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class ConfigDao extends BaseDao<Config>
{
	public List<Config> findAll()
	{
		return findList("from Config order by id");
	}
	
	public Config findByField(String field, Object value)
	{
		return find("from Config where " + field + " = ?", value);
	}
}
