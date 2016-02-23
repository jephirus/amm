package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.MessageDao;
import cn.jxust.device.model.Message;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * 控制器日志消息
 * @author Jephirus
 *
 */
@Service
@Transactional
public class MessageService extends BaseService<Message>
{
	
	public MessageDao getMessageDao(){
		return (MessageDao) baseDao;
	}

	@Override
	@Resource(name="messageDao")
	public void setBaseDao(BaseDao<Message> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<Message> getAll(PageData<Message> pageData)
	{
		return getMessageDao().findPage(pageData, "from Message order by timeLaber");
	}
}
