package cn.jxust.orm.hibernate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import cn.jxust.orm.PageData;
import cn.jxust.utils.HibernateUtils;
import cn.jxust.utils.ReflectionUtils;

/**
 * dao基类.
 * 		1：该类封装了最常见数据库操作的方法，你可以继承该类，添加自己喜欢的方法
 * 		2：当你有多个sessionFactory时，你也可以在你的子类中重写setSessionFactory()方法
 * @author yangzhibin
 *
 * @param <T> 实体类类型
 */
@SuppressWarnings("unchecked")
public class BaseDao<T>
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 构造方法
	 */
	public BaseDao()
	{
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 采用@Resource(name="xxx")按名称注入SessionFactory, 当有多个SesionFactory的时候Override本函数.
	 */
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得Session.
	 */
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 新增对象.
	 */
	public void save(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		getSession().save(entity);
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 修改对象.
	 */
	public void update(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
		logger.debug("update entity: {}", entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(Integer id)
	{
		delete(find(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(String id)
	{
		delete(find(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(Integer[] ids)
	{
		for(Integer id : ids)
		{
			delete(find(id));
			logger.debug("batch delete entity {},id is {}", entityClass.getSimpleName(), id);
		}
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(String[] ids)
	{
		for(String id : ids)
		{
			delete(find(id));
			logger.debug("batch delete entity {},id is {}", entityClass.getSimpleName(), id);
		}
	}

	/**
	 * 按id获取对象.
	 */
	public T find(Integer id)
	{
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}
	
	/**
	 * 按id获取对象.
	 */
	public T find(String id)
	{
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListByStrIds(List<String> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}
	
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListCacheByStrIds(List<String> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteriaCache(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}
	
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListByIntIds(List<Integer> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}
	
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListCacheByIntIds(List<Integer> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteriaCache(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}
	
	/**
	 * QBE分页查询.
	 */
	public PageData<T> findPage(PageData<T> pageData, T entity)
	{
		Assert.notNull(pageData, "pageData不能为空");
		Assert.notNull(entity, "entity不能为空");
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData, entity);
		pageData.setResult(criteria.list());
		return pageData;
	}
	
	/**
	 * QBE分页查询.
	 */
	public PageData<T> findPageCache(PageData<T> pageData, T entity)
	{
		Assert.notNull(pageData, "pageData不能为空");
		Assert.notNull(entity, "entity不能为空");
		Criteria criteria = HibernateUtils.createCriteriaCache(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData, entity);
		pageData.setResult(criteria.list());
		return pageData;
	}
	
	/**
	 * QBC分页查询.
	 */
	public PageData<T> findPage(PageData<T> pageData, Map<String, ?> map)
	{
		Assert.notNull(pageData, "pageData不能为空");
		Assert.notNull(map, "Map不能为空");
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData, map);
		pageData.setResult(criteria.list());
		return pageData;
	}
	
	/**
	 * QBC分页查询.
	 */
	public PageData<T> findPageCache(PageData<T> pageData, Map<String, ?> map)
	{
		Assert.notNull(pageData, "pageData不能为空");
		Assert.notNull(map, "Map不能为空");
		Criteria criteria = HibernateUtils.createCriteriaCache(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData, map);
		pageData.setResult(criteria.list());
		return pageData;
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPage(PageData<T> pageData, String hql)
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQuery(getSession(), hql);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(), pageData.getPagination().getCurrentlyPageFirstResoultIndex(), pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}
	
	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPageCache(PageData<T> pageData, String hql)
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQueryCache(getSession(), hql);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQueryCache(getSession(), pageData.getPagination().getCurrentlyPageFirstResoultIndex(), pageData.getPagination().getPageSize(), hql).list());
		return pageData;
	}
	
	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPage(PageData<T> pageData, String hql, Object... values) 
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQuery(getSession(), hql, values);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(), pageData.getPagination().getCurrentlyPageFirstResoultIndex(), pageData.getPagination().getPageSize(), hql, values).list());
		return pageData;
	}
	
	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPageCache(PageData<T> pageData, String hql, Object... values) 
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQueryCache(getSession(), hql, values);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQueryCache(getSession(), pageData.getPagination().getCurrentlyPageFirstResoultIndex(), pageData.getPagination().getPageSize(), hql, values).list());
		return pageData;
	}
	
	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPage(PageData<T> pageData, String hql, Map<String, ?> values) 
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQuery(getSession(), hql, values);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQuery(getSession(), pageData.getPagination().getPageNo(), pageData.getPagination().getPageSize(), hql, values).list());
		return pageData;
	}
	
	/**
	 * HQL分页查询.
	 */
	public PageData<T> findPageCache(PageData<T> pageData, String hql, Map<String, ?> values) 
	{
		Assert.notNull(pageData, "pageData不能为空");
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = HibernateUtils.countQueryCache(getSession(), hql);
			pageData.getPagination().setTotalCount(totalCount);
		}
		pageData.setResult(HibernateUtils.createQueryCache(getSession(), pageData.getPagination().getPageNo(), pageData.getPagination().getPageSize(), hql, values).list());
		return pageData;
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X find(String hql, Object... values)
	{
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X findCache(String hql, Object... values)
	{
		return (X) HibernateUtils.createQueryCache(getSession(), hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <X> X find(String hql, Map<String, ?> values)
	{
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <X> X findCache(String hql, Map<String, ?> values)
	{
		return (X) HibernateUtils.createQueryCache(getSession(), hql, values).uniqueResult();
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql)
	{
		return HibernateUtils.createQuery(getSession(), hql).list();
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findListCache(String hql)
	{
		return HibernateUtils.createQueryCache(getSession(), hql).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql, Object... values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findListCache(String hql, Object... values)
	{
		return HibernateUtils.createQueryCache(getSession(), hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	public <X> List<X> findListCache(String hql, Map<String, ?> values)
	{
		return HibernateUtils.createQueryCache(getSession(), hql, values).list();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Object... values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Map<String, ?> values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 本地SQL进行修改/删除操作.
	 * @return 更新记录数.
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQL(String sql)
	{
		Assert.hasText(sql, "sql不能为空");
		return getSession().createSQLQuery(sql).list();
	}
	
	
	public long countNum(String hql) {
		// TODO Auto-generated method stub
		
		long count = (Long) HibernateUtils.createQuery(getSession(), hql).uniqueResult();
		return count;
	}

}
