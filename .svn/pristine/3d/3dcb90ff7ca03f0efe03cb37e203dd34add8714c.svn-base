package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.ConfigDao;
import cn.jxust.base.model.Config;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class ConfigService extends BaseService<Config>
{
	@Override
	@Resource(name = "configDao")
	public void setBaseDao(BaseDao<Config> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public ConfigDao getConfigDao()
	{
		return (ConfigDao)this.baseDao;
	}
	
	@Transactional(readOnly = true)
	public List<Config> getAll()
	{
		return getConfigDao().findAll();
	}
	
	@Transactional(readOnly = true)
	public Config getFieldValue(String field, Object value)
	{
		return getConfigDao().findByField(field, value);
	}
}
