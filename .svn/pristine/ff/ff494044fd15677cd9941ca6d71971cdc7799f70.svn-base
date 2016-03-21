package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.ExplorerMessageDao;
import cn.jxust.device.model.ExplorerMessage;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
@Service
@Transactional
public class ExplorerMessageService extends BaseService<ExplorerMessage>{

	public ExplorerMessageDao getExploreMessageDao(){
		return (ExplorerMessageDao) baseDao;
	}
	@Override
	@Resource(name="explorerMessageDao")
	public void setBaseDao(BaseDao<ExplorerMessage> baseDao) {
		this.baseDao=baseDao;
	}
	public PageData<ExplorerMessage> getAll(PageData<ExplorerMessage> pageData)
	{
		return getExploreMessageDao().findPage(pageData, "from ExplorerMessage order by timeLaber");
	}
}
