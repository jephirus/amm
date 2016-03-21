/**
 * 
 */
package cn.jxust.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Department;
import cn.jxust.log.dao.AttachDeviceLogDao;
import cn.jxust.log.model.AttachDeviceLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * @author Jephirus
 *
 */
@Service
@Transactional
public class AttachDeviceLogService extends BaseService<AttachDeviceLog>{

	public PageData<AttachDeviceLog> getAllAttachDeviceLog(PageData<AttachDeviceLog> pageData)
	{
		return getAttachDeviceLogDao().findPage(pageData);
	}

	/**
	 * 有单位查询
	 * @param pageData
	 * @param department 用户单位
	 * @return
	 */
	public PageData<AttachDeviceLog> getAllAttachDeviceLog(PageData<AttachDeviceLog> pageData, Department department)
	{
		return getAttachDeviceLogDao().findPage(pageData, department);
	}

	@Override
	@Resource(name="attachDeviceLogDao")
	public void setBaseDao(BaseDao<AttachDeviceLog> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public AttachDeviceLogDao getAttachDeviceLogDao()
	{
		return (AttachDeviceLogDao)baseDao;
	}
	
}
