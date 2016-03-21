package cn.jxust.device.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.ProberDao;
import cn.jxust.device.model.Prober;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * 报警器
 * @author Jephirus
 *
 */
@Service
@Transactional
public class ProberService extends BaseService<Prober>
{
	
	public ProberDao getProberDao(){
		return (ProberDao) baseDao;
	}

	@Override
	@Resource(name="proberDao")
	public void setBaseDao(BaseDao<Prober> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<Prober> getAll(PageData<Prober> pageData)
	{
		return getProberDao().findPage(pageData, "from Prober");
	}
	
	/**
	 * @param proberId 探测器id
	 * @return
	 */
	public List<Prober> findByProberId(String proberId){
		return getProberDao().findByProberId(proberId);
	}
	
	public List<Prober> findAll()
	{
		return getProberDao().findList("from Prober");
	}
}
