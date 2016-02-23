package cn.jxust.utils;

import java.util.List;
import java.util.Map;

import cn.jxust.utils.freemarker.TemplateUtil;
import cn.jxust.utils.freemarker.TemplateUtilFactory;

public class HtmlUtils
{
	public static boolean CreateList(String pageFtl, String listFtl, String savepath, String profix, List<?> data, Map<String, Object> values) throws Exception
	{
		int onepage = 20;

		savepath = savepath.replaceAll("\\\\", "/");

		int length = data.size();
		TemplateUtil tutil = TemplateUtilFactory.getInstance();
		if (length > onepage)
		{
			data = data.subList(0, onepage);
		}
		values.put("data", data);
		tutil.output(pageFtl, savepath + profix + ".html", values);
		tutil.outputList(listFtl, savepath, profix, data, onepage, values);
		return true;
	}

	public static boolean CreatePage(String ftl, String htmlpath, Map<String, Object> values) throws Exception
	{
		TemplateUtilFactory.getInstance().output(ftl, htmlpath, values);
		return true;
	}
	
}
