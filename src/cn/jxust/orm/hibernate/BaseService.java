package cn.jxust.orm.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.jxust.orm.PageData;

/**
 * service基类.
 * @author yangzhibin
 *
 * @param <T> 实体类类型
 */
@Transactional
public abstract class BaseService<T>
{
	protected BaseDao<T> baseDao;

	public BaseDao<T> getBaseDao()
	{
		return baseDao;
	}

	
	public abstract void setBaseDao(BaseDao<T> baseDao);

	/**
	 * 新增对象.
	 */
	public void save(T entity)
	{
		getBaseDao().save(entity);
	}

	/**
	 * 修改对象.
	 */
	public void update(T entity)
	{
		getBaseDao().update(entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(T entity)
	{
		getBaseDao().delete(entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(Integer id)
	{
		getBaseDao().delete(id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(String id)
	{
		getBaseDao().delete(id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(Integer[] ids)
	{
		getBaseDao().delete(ids);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(String[] ids)
	{
		getBaseDao().delete(ids);
	}

	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public T find(Integer id)
	{
		return getBaseDao().find(id);
	}
	
	
	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public T find(String id)
	{
		return getBaseDao().find(id);
	}

	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListByIntIds(List<Integer> idList)
	{
		return getBaseDao().findListByIntIds(idList);
	}
	
	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListCacheByIntIds(List<Integer> idList)
	{
		return getBaseDao().findListCacheByIntIds(idList);
	}
	
	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListByStrIds(List<String> idList)
	{
		return getBaseDao().findListByStrIds(idList);
	}
	
	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListCacheByStrIds(List<String> idList)
	{
		return getBaseDao().findListCacheByStrIds(idList);
	}
	
	/**
	 * QBE分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPage(PageData<T> pageData, T entity)
	{
		return getBaseDao().findPage(pageData, entity);
	}
	
	/**
	 * QBE分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPageCache(PageData<T> pageData, T entity)
	{
		return getBaseDao().findPageCache(pageData, entity);
	}
	
	/**
	 * QBC分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPage(PageData<T> pageData, Map<String, Object> map)
	{
		return getBaseDao().findPage(pageData, map);
	}
	
	/**
	 * QBC分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPageCache(PageData<T> pageData, Map<String, Object> map)
	{
		return getBaseDao().findPageCache(pageData, map);
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Object... values)
	{
		return getBaseDao().find(hql, values);
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X findCache(String hql, Object... values)
	{
		return getBaseDao().findCache(hql, values);
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Map<String, ?> values)
	{
		return getBaseDao().find(hql, values);
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X findCache(String hql, Map<String, ?> values)
	{
		return getBaseDao().findCache(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Object... values)
	{
		return getBaseDao().findList(hql, values);
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findListCache(String hql, Object... values)
	{
		return getBaseDao().findListCache(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users "
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql)
	{
		return getBaseDao().findList(hql);
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users "
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findListCache(String hql)
	{
		return getBaseDao().findListCache(hql);
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		return getBaseDao().findList(hql, values);
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findListCache(String hql, Map<String, ?> values)
	{
		return getBaseDao().findListCache(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Object... values)
	{
		return getBaseDao().batchExecute(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Map<String, ?> values)
	{
		return getBaseDao().batchExecute(hql, values);
	}
	
	/**
	 * HQL分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPage(PageData<T> pageData, String hql)
	{
		return getBaseDao().findPage(pageData, hql);
	}
	
	/**
	 * HQL分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> findPageCache(PageData<T> pageData, String hql)
	{
		return getBaseDao().findPageCache(pageData, hql);
	}
	
	/**
	 * 得到不同记录条数
	 * @param hql
	 * @return
	 */
	public long countNum(String hql){
		return getBaseDao().countNum(hql);
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 本地SQL进行修改/删除操作.
	 * @return 更新记录数.
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List findBySQL(String sql)
	{
		return getBaseDao().findBySQL(sql);
	}
}
