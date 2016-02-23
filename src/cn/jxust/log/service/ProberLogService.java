package cn.jxust.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Department;
import cn.jxust.log.dao.ProberLogDao;
import cn.jxust.log.model.ProberLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class ProberLogService extends BaseService<ProberLog>
{
	public PageData<ProberLog> getAllProberLog(PageData<ProberLog> pageData)
	{
		return getProberLogDao().findPage(pageData);
	}

	/**
	 * 带单位查询
	 * @param pageData
	 * @param department
	 * @return
	 */
	public PageData<ProberLog> getAllProberLog(PageData<ProberLog> pageData, Department department)
	{
		return getProberLogDao().findPage(pageData, department);
	}

	@Override
	@Resource(name="proberLogDao")
	public void setBaseDao(BaseDao<ProberLog> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public ProberLogDao getProberLogDao()
	{
		return (ProberLogDao)baseDao;
	}
	
}
