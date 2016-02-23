package cn.jxust.utils.freemarker;

public class TemplateUtilFactory
{
	private static TemplateUtil template = new TemplateUtil();
	
	private TemplateUtilFactory()
	{
	}
	
	/**
	 * Template工厂，用来得到TemplateUtil实例
	 * @return
	 */
	public synchronized static TemplateUtil getInstance()
	{
		if(null == template)
		{
			template = new TemplateUtil();
		}
		return template;
	}
}
