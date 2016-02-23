/**
 * 
 */
package cn.jxust.log.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.jxust.base.model.Department;
import cn.jxust.log.model.AttachDeviceLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.utils.HibernateUtils;

/**
 * @author Jephirus
 *
 */
@Repository
public class AttachDeviceLogDao extends BaseDao<AttachDeviceLog> {

	/**
	 * 该方法查询所有单位，用于超级管理员和超级游客。
	 * HQL分页查询. 重写分页查询，Hql有差异。
	 */
	@SuppressWarnings("unchecked")
	public PageData<AttachDeviceLog> findPage(PageData<AttachDeviceLog> pageData) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.AttachDeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, em.deviceStatus, em.timeLaber, em.deviceAddress) from ExplorerMessage as em, Device as d where CONCAT(em.infoID,em.sysAddress)=d.deviceCode order by em.timeLaber";
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
	 * 重写分页查询，该方法带单位， 用于单位用户及单位管理员、单位游客
	 * @param pageData  '" + department.getDepartmentName() + "' 一定要加 : '
	 * @param department
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData<AttachDeviceLog> findPage(PageData<AttachDeviceLog> pageData, Department department) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.AttachDeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, em.deviceStatus, em.timeLaber, em.deviceAddress) from ExplorerMessage as em, Device as d where em.infoID=d.deviceCode and d.area.department.departmentName='"+ department.getDepartmentName() +"' order by em.timeLaber";
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount = HibernateUtils
					.countQuery(getSession(), "from ExplorerMessage as em, Device as d where em.infoID=d.deviceCode and d.area.department.departmentName='" + department.getDepartmentName() + "' order by em.timeLaber");
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}
}
