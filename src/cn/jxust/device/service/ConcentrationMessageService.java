package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.ConcentrationMessageDao;
import cn.jxust.device.model.ConcentrationMessage;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
@Service
@Transactional
public class ConcentrationMessageService extends BaseService<ConcentrationMessage>{
	
	
	public ConcentrationMessageDao getConcentrationMessageDao(){
		return (ConcentrationMessageDao) baseDao;
	}
	@Override
	@Resource(name="concentrationMessageDao")
	public void setBaseDao(BaseDao<ConcentrationMessage> baseDao) {
		this.baseDao=baseDao;
	}
	public PageData<ConcentrationMessage> getAll(PageData<ConcentrationMessage> pageData)
	{
		return getConcentrationMessageDao().findPage(pageData, "from ConcentrationMessage order by timeLaber");
	}

}
