package cn.jxust.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.device.model.AttachDevice;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class AttachDeviceDao extends BaseDao<AttachDevice>
{

	/**
	 * @param proberId 报警器Id
	 * @return
	 */
	public List<AttachDevice> findByAttachDeviceId(String attachDeviceId) {
		return findList("from AttachDevice ad where ad.attachDeviceId = "+attachDeviceId);
	}
}
