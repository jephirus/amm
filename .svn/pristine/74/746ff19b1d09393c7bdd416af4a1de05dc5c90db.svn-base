package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.SysStateMessageDao;
import cn.jxust.device.model.SysStateMessage;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * 控制器
 * @author Jephirus
 *
 */
@Service
@Transactional
public class SysStateMessageService extends BaseService<SysStateMessage>{
	
	
	public SysStateMessageDao getSysStateMessageDao(){
		return (SysStateMessageDao) baseDao;
	}
	@Override
	@Resource(name="sysStateMessageDao")
	public void setBaseDao(BaseDao<SysStateMessage> baseDao) {
		this.baseDao =baseDao;
		
	}
	
	public PageData<SysStateMessage> getAll(PageData<SysStateMessage> pageData)
	{
		return getSysStateMessageDao().findPage(pageData, "from SysStateMessage order by timeLaber");
	}

}
