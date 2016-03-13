/**
 * 
 */
package cn.jxust.log.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.jxust.base.model.Department;
import cn.jxust.log.model.DeviceLog;
import cn.jxust.log.model.ProberLog;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.utils.HibernateUtils;

/**
 * @author Jephirus
 *
 */
@Repository
public class DeviceLogDao extends BaseDao<DeviceLog> {

	/**
	 * HQL分页查询. 重写分页查询，Hql有差异。
	 */
	@SuppressWarnings("unchecked")
	public PageData<DeviceLog> findPage(PageData<DeviceLog> pageData) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.DeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, ssm.controllerStatus, ssm.timeLaber) from SysStateMessage as ssm, Device as d where CONCAT(ssm.infoID,ssm.sysAddress)=d.deviceCode order by ssm.timeLaber";
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount = HibernateUtils
					.countQuery(
							getSession(),
							"from SysStateMessage as ssm, Device as d where CONCAT(ssm.infoID,ssm.sysAddress)=d.deviceCode order by ssm.timeLaber");
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}

	/**
	 * 带单位的查询
	 * 
	 * @param pageData
	 * @param department
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData<DeviceLog> findPage(PageData<DeviceLog> pageData,
			Department department) {
		Assert.notNull(pageData, "pageData不能为空");
		String hql = "select new cn.jxust.log.model.DeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, ssm.controllerStatus, ssm.timeLaber) from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode and d.area.department.departmentName='"
				+ department.getDepartmentName() + "' order by ssm.timeLaber";
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount = HibernateUtils
					.countQuery(
							getSession(),
							"from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode and d.area.department.departmentName='"
									+ department.getDepartmentName()
									+ "' order by ssm.timeLaber");
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}

	@SuppressWarnings("unchecked")
	public PageData<DeviceLog> getDeviceLogByQuery(
			PageData<DeviceLog> pageData, Integer deviceCode, String proberNum,
			String beginDate, String endDate, int pageNum, Department department) {
		StringBuilder sb = new StringBuilder();
		Assert.notNull(pageData, "pageData不能为空");
		String hql;
		if (department != null) {
			hql = "select new cn.jxust.log.model.DeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, ssm.controllerStatus, ssm.timeLaber) from SysStateMessage as ssm, Device as d where ssm.infoID = "
					+ deviceCode
					+ " and d.area.department.departmentName='"
					+ department.getDepartmentName()
					+ "' order by ssm.timeLaber";
		} else {
			hql = "select new cn.jxust.log.model.DeviceLog(d.deviceName, d.area.areaName,d.area.department.departmentName, ssm.controllerStatus, ssm.timeLaber) from SysStateMessage as ssm, Device as d where ssm.infoID = "
					+ deviceCode + " order by ssm.timeLaber";
		}
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount;
			if (department != null) {
				totalCount = HibernateUtils
						.countQuery(
								getSession(),
								"from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode and d.area.department.departmentName='"
										+ department.getDepartmentName()
										+ "' order by ssm.timeLaber");
			} else {
				totalCount = HibernateUtils
						.countQuery(
								getSession(),
								"from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode order by ssm.timeLaber");
			}
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;

	}

	@SuppressWarnings("unchecked")
	public PageData<ProberLog> getProberLogByQuery(
			PageData<ProberLog> pageData, Integer deviceId, String proberNum,
			String beginDate, String endDate, int pageNum, Department department) {
		StringBuilder sb = new StringBuilder();
		Assert.notNull(pageData, "pageData不能为空");
		String hql;
		if (department != null) {
			hql = "select new cn.jxust.log.model.ProberLog(d.deviceName, d.area.areaName,d.area.department.departmentName, em.deviceStatus, em.timeLaber) from ExplorerMessage as em, Device as d where ssm.infoID ="
					+ deviceId
					+ " and d.area.department.departmentName='"
					+ department.getDepartmentName()
					+ "' order by em.timeLaber";
		} else {
			hql = "select new cn.jxust.log.model.ProberLog(d.deviceName, d.area.areaName,d.area.department.departmentName, em.deviceStatus, em.timeLaber) from ExplorerMessage as em, Device as d where em.infoID ="
					+ deviceId + " order by em.timeLaber";
		}
		if (pageData.getPagination().isReadTotalCount()) {
			long totalCount;
			if (department != null) {
				totalCount = HibernateUtils
						.countQuery(
								getSession(),
								"from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode and d.area.department.departmentName='"
										+ department.getDepartmentName()
										+ "' order by ssm.timeLaber");
			} else {
				totalCount = HibernateUtils
						.countQuery(
								getSession(),
								"from SysStateMessage as ssm, Device as d where ssm.infoID = d.deviceCode order by ssm.timeLaber");
			}
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(),
				pageData.getPagination().getCurrentlyPageFirstResoultIndex(),
				pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}

}
