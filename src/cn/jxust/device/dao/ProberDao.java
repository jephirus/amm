package cn.jxust.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.device.model.Prober;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class ProberDao extends BaseDao<Prober>
{

	/**
	 * @param proberId 报警器Id
	 * @return
	 */
	public List<Prober> findByProberId(String proberId) {
		return findList("from Prober prober where prober.proberId = "+proberId);
	}
}
