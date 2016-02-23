package cn.jxust.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import cn.jxust.orm.PageData;

/**
 * hibernate工具类
 * @author yangzhibin
 *
 */
public class HibernateUtils
{
	/**
	 * 根据Criterion条件创建Criteria.
	 */
	public static Criteria createCriteria(Session session, Class<?> entityClass, Criterion... criterions)
	{
		Criteria criteria = session.createCriteria(entityClass);
		for (Criterion criterion : criterions)
		{
			criteria.add(criterion);
		}
		return criteria;
	}
	
	/**
	 * 根据Criterion条件创建Criteria.
	 */
	public static Criteria createCriteriaCache(Session session, Class<?> entityClass, Criterion... criterions)
	{
		Criteria criteria = session.createCriteria(entityClass);
		criteria.setCacheable(true);
		for (Criterion criterion : criterions)
		{
			criteria.add(criterion);
		}
		return criteria;
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQuery(Session session, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQueryCache(Session session, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		query.setCacheable(true);
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQuery(Session session, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQueryCache(Session session, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		
		query.setCacheable(true);

		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQuery(Session session, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		if (values != null)
		{
			query.setProperties(values);
		}
		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 */
	public static Query createQueryCache(Session session, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		if (values != null)
		{
			query.setProperties(values);
		}
		query.setCacheable(true);
		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQuery(Session session, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQueryCache(Session session, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		query.setCacheable(true);
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQuery(Session session, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		if (values != null)
		{
			query.setProperties(values);
		}
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQueryCache(Session session, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		if (values != null)
		{
			query.setProperties(values);
		}
		query.setCacheable(true);
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQuery(Session session, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表统计记录总数.
	 */
	public static long countQueryCache(Session session, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery("select count(*) " + hql);
		query.setCacheable(true);
		return (Long)query.list().get(0);  
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQuery(Session session, int pageNumber, int pageSize, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQueryCache(Session session, int pageNumber, int pageSize, String hql, Object... values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);
		
		query.setCacheable(true);

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQuery(Session session, int pageNumber, int pageSize, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		if (values != null)
		{
			query.setProperties(values);
		}
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQueryCache(Session session, int pageNumber, int pageSize, String hql, Map<String, ?> values)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);

		if (values != null)
		{
			query.setProperties(values);
		}
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);
		
		query.setCacheable(true);

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQuery(Session session, int pageNumber, int pageSize, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);

		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建分页结果List对象.
	 */
	public static Query createQueryCache(Session session, int pageNumber, int pageSize, String hql)
	{
		Assert.hasText(hql, "hql不能为空");
		Query query = session.createQuery(hql);
		
		query.setFirstResult(pageNumber);
		query.setMaxResults(pageSize);
		
		query.setCacheable(true);

		return query;
	}
	
//	/**
//	 * 创建Criterion
//	 */
//	private static Criterion createCriterion(String fieldName, Object fieldValue, MatchType matchType)
//	{
//		Criterion criterion = null;
//		Assert.hasText(fieldName, "fieldName不能为空");
//		switch (matchType)
//		{
//		case EQ: // =
//			criterion = Restrictions.eq(fieldName, fieldValue);
//			break;
//		case LIKE: // like
//			criterion = Restrictions.like(fieldName, (String) fieldValue, MatchMode.ANYWHERE);
//			break;
//		case LT: // <
//			criterion = Restrictions.lt(fieldName, fieldValue);
//			break;
//		case LE: // <=
//			criterion = Restrictions.le(fieldName, fieldValue);
//			break;
//		case GT: // >
//			criterion = Restrictions.gt(fieldName, fieldValue);
//			break;
//		case GE: // >=
//			criterion = Restrictions.ge(fieldName, fieldValue);
//			break;
//		}
//		return criterion;
//	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static long countCriteriaResult(Criteria criteria)
	{
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer resultTransformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
		ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());

		// 执行Count查询
		Object obj = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		if(null != obj)
		{
			totalCount = (Long) obj;
		}

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		criteria.setProjection(projection);

		if (projection == null)
		{
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (resultTransformer != null)
		{
			criteria.setResultTransformer(resultTransformer);
		}

		ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);

		return totalCount;
	}

//	/**
//	 * 设置排序参数到Criteria对象
//	 */
//	public static Criteria setCompositorParameter(Criteria criteria, Compositor compositor)
//	{
//		if (compositor != null)
//		{
//			String fieldName = compositor.getFieldName();
//			CompositorType compositorType = compositor.getCompositorType();
//			switch (compositorType)
//			{
//			case ASC:
//				criteria.addOrder(Order.asc(fieldName));
//				break;
//			case DESC:
//				criteria.addOrder(Order.desc(fieldName));
//				break;
//			}
//		}
//
//		return criteria;
//	}

//	/**
//	 * 设置过滤条件到Criteria对象
//	 */
//	public static Criteria setFiltrationParameter(Criteria criteria, Filtration... filtrations)
//	{
//		if (filtrations.length > 0)
//		{
//			List<Criterion> criterions = new ArrayList<Criterion>();
//			for (Filtration filtration : filtrations)
//			{
//				Criterion criterion = null;
//				if (!filtration.isMultiFilter())
//				{
//					criterion = createCriterion(filtration.getFieldName(), filtration.getFieldValue(), filtration.getMatchType());
//					criterions.add(criterion);
//
//				} else
//				{
//					//包含多个属性需要比较的情况,进行or处理.
//					Disjunction disjunction = Restrictions.disjunction();
//					for (String filedName : filtration.getFieldNames())
//					{
//						criterion = createCriterion(filedName, filtration.getFieldValue(), filtration.getMatchType());
//						disjunction.add(criterion);
//					}
//					criterions.add(disjunction);
//				}
//			}
//			for (Criterion criterion : criterions)
//			{
//				criteria.add(criterion);
//			}
//		}
//
//		return criteria;
//	}

	/**
	 * 设置分页参数map到Criteria对象
	 */
	public static Criteria setParameter(Criteria criteria, PageData<?> pageData, Map<String, ?> map)
	{
		//第1步：读取记录总数
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = countCriteriaResult(criteria);
			pageData.getPagination().setTotalCount(totalCount);
		}
		//第2步：设置查询条件
		if (map != null)  
		{  
			Set<String> keys = map.keySet();  
			for (String key : keys)  
			{  
				criteria.add(Restrictions.like(key, map.get(key)));  
			}  
		}  

		//第3步：设置查询范围
		criteria.setFirstResult(pageData.getPagination().getCurrentlyPageFirstResoultIndex());
		criteria.setMaxResults(pageData.getPagination().getPageSize());

		return criteria;
	}
	
	/**
	 * 设置分页参数object到Criteria对象
	 */
	public static Criteria setParameter(Criteria criteria, PageData<?> pageData, Object object)
	{
		//第1步：设置查询条件
		if (object != null)  
		{  
		    criteria.add(Example.create(object).enableLike());  
		}  
		
		//第2步：读取记录总数
		if (pageData.getPagination().isReadTotalCount())
		{
			long totalCount = countCriteriaResult(criteria);
			pageData.getPagination().setTotalCount(totalCount);
		}

		//第3步：设置查询范围
		criteria.setFirstResult(pageData.getPagination().getCurrentlyPageFirstResoultIndex());
		criteria.setMaxResults(pageData.getPagination().getPageSize());

		return criteria;
	}
}
