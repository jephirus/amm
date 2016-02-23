package cn.jxust.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.jxust.orm.PageData;

/**
 * web工具类
 * @author yangzhibin
 *
 */
public class WebUtils
{
	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix)
	{
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null)
		{
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix))
			{
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0)
				{
					// Do nothing, no values found at all.
				} else if (values.length > 1)
				{
					params.put(unprefixed, values);
				} else
				{
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	public static void setPageDataParameter(HttpServletRequest request, PageData<?> pageData)
	{
		//第1步：设置当前页
		String pageNoStr = request.getParameter("pageNo");
		if (StringUtils.isNotBlank(pageNoStr))
		{
			Integer pageNo = Integer.parseInt(pageNoStr);
			pageData.setPageNo(pageNo);
		}
	}
}
