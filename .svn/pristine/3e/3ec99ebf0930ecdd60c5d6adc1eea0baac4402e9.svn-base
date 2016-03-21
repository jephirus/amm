package cn.jxust.sms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.sms.dao.SMSDao;
import cn.jxust.sms.model.SMS;

@Service(value="smsService")
@Transactional
public class SMSService extends BaseService<SMS>
{
	
	public SMSDao getSMSDao(){
		return (SMSDao) baseDao;
	}

	@Override
	@Resource(name="smsDao")
	public void setBaseDao(BaseDao<SMS> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<SMS> getAll(PageData<SMS> pageData)
	{
		return getSMSDao().findPage(pageData, "from SMS");
	}
	
	/**
	 * 通过设备id(不是主键)获取短信
	 * @param deviceId 设备id
	 * @return
	 */
	public List<SMS> findByDeviceId(String deviceId){
		return getSMSDao().findByDeviceId(deviceId);
	}
}
