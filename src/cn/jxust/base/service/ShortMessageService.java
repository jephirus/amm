package cn.jxust.base.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.ShortMessageDao;
import cn.jxust.base.model.ShortMessage;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class ShortMessageService extends BaseService<ShortMessage>
{
	@Override
	@Resource(name="shortMessageDao")
	public void setBaseDao(BaseDao<ShortMessage> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public ShortMessageDao getShortMessageDao()
	{
		return (ShortMessageDao)baseDao;
	}
	
	public void save(ShortMessage shortMessage, String deptId, String RoleId)
	{
		getShortMessageDao().save(shortMessage);
	}
}
