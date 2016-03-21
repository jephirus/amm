package cn.jxust.device.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.AttachDeviceDao;
import cn.jxust.device.model.AttachDevice;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * 外控制器
 * @author Jephirus
 *
 */
@Service
@Transactional
public class AttachDeviceService extends BaseService<AttachDevice>
{
	
	public AttachDeviceDao getAttachDeviceDao(){
		return (AttachDeviceDao) baseDao;
	}

	@Override
	@Resource(name="attachDeviceDao")
	public void setBaseDao(BaseDao<AttachDevice> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<AttachDevice> getAll(PageData<AttachDevice> pageData)
	{
		return getAttachDeviceDao().findPage(pageData, "from AttachDevice");
	}
	
	/**
	 * @param attachDeviceId  外控制器id
	 * @return
	 */
	public List<AttachDevice> findByAttachDeviceId(String attachDeviceId){
		return getAttachDeviceDao().findByAttachDeviceId(attachDeviceId);
	}
	
	public List<AttachDevice> findAll()
	{
		return getAttachDeviceDao().findList("from AttachDevice");
	}
}
