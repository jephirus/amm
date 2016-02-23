package cn.jxust.orm;

/**
 * 分页
 * @author yangzhibin
 *
 */
public class Pagination
{
	private int pageNo = 1;//当前页号
	private int pageSize = 20;//每页显示记录条数，默认20条
	private long totalCount = 0;//总记录条数
	private boolean isReadTotalCount = true;//是否读取总记录条数

	/**
	 * 获取当前页号.
	 */
	public int getPageNo()
	{
		return pageNo;
	}

	/**
	 * 设置当前页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo < 1 ? 1 : pageNo;
	}

	/**
	 * 获取每页显示记录条数.
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * 设置每页的记录条数,低于2时自动调整为20.
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize < 2 ? 20 : pageSize;
	}

	/**
	 * 获取总记录条数.
	 */
	public long getTotalCount()
	{
		return totalCount;
	}

	/**
	 * 设置总记录条数.
	 */
	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}

	/**
	 * 是否读取总记录条数.
	 */
	public boolean isReadTotalCount()
	{
		return isReadTotalCount;
	}

	/**
	 * 设置是否读取总记录条数.
	 */
	public void setReadTotalCount(boolean isReadTotalCount)
	{
		this.isReadTotalCount = isReadTotalCount;
	}

	/**
	 * 获取总页数(最后一页的页号).
	 */
	public long getTotalPage()
	{
		if (totalCount <= 0)
		{
			return 1;
		} else
		{
			long count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
			{
				count++;
			}
			return count;
		}
	}

	/**
	 * 是否有上一页.
	 */
	public boolean isHasPrevPage()
	{
		return (pageNo - 1 >= 1);
	}

	/**
	 * 获取上一页的页号.
	 */
	public int getPrevPage()
	{
		return isHasPrevPage() ? (pageNo - 1) : pageNo;
	}

	/**
	 * 是否有下一页.
	 */
	public boolean isHasNextPage()
	{
		if (isReadTotalCount)
		{
			return (pageNo + 1 <= getTotalPage());
		} else
		{
			return true;//在没有读取总记录条数的情况下，都有下一页
		}

	}

	/**
	 * 获取下一页的页号.
	 */
	public int getNextPage()
	{
		return isHasNextPage() ? (pageNo + 1) : pageNo;
	}

	/**
	 * 获取当前页面上第一条记录对应数据库中的索引.
	 */
	public int getCurrentlyPageFirstResoultIndex()
	{
		return (pageNo - 1) * pageSize;
	}
}
