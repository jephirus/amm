package cn.jxust.sms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.sms.model.SMS;

@Repository(value="smsDao")
public class SMSDao extends BaseDao<SMS>
{

	public List<SMS> findByDeviceId(String deviceId) {
		return findList("from SMS s where s.deviceId = "+deviceId);
	}
}
