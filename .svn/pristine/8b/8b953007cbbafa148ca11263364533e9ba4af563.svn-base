package cn.jxust.orm;

import java.util.List;

/**
 * 页面数据
 * @author yangzhibin
 *
 */
public class PageData<T>
{
	private List<T> result;//页面数据列表
	private Pagination pagination = new Pagination();//分页

	public PageData()
	{
		
	}
	
	public PageData(int pageNo)
	{
		pagination.setPageNo(pageNo);
	}
	
	/**
	 * 获取页面数据列表.
	 */
	public List<T> getResult()
	{
		return result;
	}

	/**
	 * 设置页面数据列表.
	 */
	public void setResult(List<T> result)
	{
		this.result = result;
	}

	/**
	 * 获取分页信息.
	 */
	public Pagination getPagination()
	{
		return pagination;
	}

	/**
	 * 设置分页信息.
	 */
	public void setPagination(Pagination pagination)
	{
		this.pagination = pagination;
	}

	/**
	 * 设置当前页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(int pageNo)
	{
		pagination.setPageNo(pageNo);
	}

	/**
	 * 设置每页的记录数量,低于2时自动调整为20.
	 */
	public void setPageSize(int pageSize)
	{
		pagination.setPageSize(pageSize);
	}
}
