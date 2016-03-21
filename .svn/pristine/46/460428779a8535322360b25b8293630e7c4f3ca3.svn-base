/**
 * 
 */
package cn.jxust.log.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.jxust.base.model.Department;
import cn.jxust.log.model.ProberLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.utils.HibernateUtils;

/**
 * @author Jephirus
 *
 */
@Repository
public class ProberLogDao extends BaseDao<ProberLog> {

	/**
	 * HQL分页查询. 重写分页查询，Hql有差异。
	 */
	@SuppressWarnings("unchecked")
	public PageData<ProberLog> findPage(PageData<ProberLog> pageData) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.ProberLog(d.deviceName, d.area.areaName, d.area.department.departmentName, em.deviceStatus, em.timeLaber, em.sysAddress, em.deviceType) from ExplorerMessage as em, Device as d where CONCAT(em.infoID,em.sysAddress)=d.deviceCode order by em.timeLaber";
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount = HibernateUtils
					.countQuery(
							getSession(), "from ExplorerMessage as em, Device as d where CONCAT(em.infoID,em.sysAddress)=d.deviceCode order by em.timeLaber");
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}

	/**
	 * 带单位为条件的分页查询
	 * @param pageData
	 * @param department
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData<ProberLog> findPage(PageData<ProberLog> pageData, Department department) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.ProberLog(d.deviceName, d.area.areaName, d.area.department.departmentName, em.deviceStatus, em.timeLaber, em.sysAddress, em.deviceType) from ExplorerMessage as em, Device as d where em.infoID=d.deviceCode and d.area.department.departmentName='"+ department +"' order by em.timeLaber";
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount = HibernateUtils
					.countQuery(
							getSession(), "from ExplorerMessage as em, Device as d where em.infoID=d.deviceCode and d.area.department.departmentName='"+ department +"' order by em.timeLaber");
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}

}
