package cn.jxust.utils.freemarker;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TemplateUtil
{
	private static Configuration cfg = new Configuration();

	static
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template/");
		
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		cfg.setDefaultEncoding("utf-8");
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		cfg.setOutputEncoding("utf-8");
		cfg.setLocalizedLookup(false);
	}

	/**
	 * 通过模版生成静态页面 读取web的classpath下建立template文件夹存放ftl文件
	 */
	public void output(String ftl, String savepath, Map<String, ?> values) throws Exception
	{
		Template template = null;
		Writer outs = null;

		FileOutputStream fos = null;
		OutputStreamWriter out = null;
		try
		{
			template = cfg.getTemplate(ftl);

			fos = new FileOutputStream(savepath);
			out = new OutputStreamWriter(fos, "utf-8");
			outs = new BufferedWriter(out);

			template.process(values, outs);
			outs.flush();
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			if (fos != null)
			{
				fos.close();
			}
			if (out != null)
			{
				out.close();
			}
			if (outs != null)
			{
				outs.close();
			}
		}
	}

	/**
	 * 通过模版进行分页处理生成静态页面 读取web的classpath下建立template文件夹存放ftl文件
	 * 外部需引入pager的css
	 * 
	 * @param ftl classpath下的模版
	 * @param savepath 保存路径 如D://web/
	 * @param profix 文件前缀 如list_ 将得到 list_0.html
	 * @param data 数据列表
	 * @param onepage 一页数据条数
	 * @param values 值
	 * @throws Exception
	 */
	public void outputList(String ftl, String savepath, String profix, List<?> data, int onepage, Map<String, Object> values) throws Exception
	{
		int all = data.size();// 记录总数
		int page = onepage;// 每页记录
		
		// 总页数
		int count = all / page;

		if (all % page != 0)
		{
			count++;
		}

		//空记录
		if(0 == all)
		{
			values.put("pager", "");
			this.output(ftl, savepath + profix + "_1.html", values);
		}
		
		for (int i = 1; i <= all; i++)
		{
			// 只有一页
			if (i < page)
			{
				values.put("data", data);
				String pager = "<div id='pager'> 1/1页 </div>";
				values.put("pager", pager);
				this.output(ftl, savepath + profix + "_1.html", values);
			}
			// 第一页
			else if(i == page)
			{
				values.put("data", data.subList(0, i));
				String pager = "<div id='pager'>1/{count}页 首页 上一页 <a href='"+profix+"_2.html'>下一页</a> <a href='"+profix+"_{count}.html'>末页</a></div>";
				pager = pager.replaceAll("\\{count\\}", String.valueOf(count));
				values.put("pager", pager);
				this.output(ftl, savepath + profix + "_1.html", values);
			}
			// 不是第一页
			else
			{
				int j = i % page;
				int k = i / page;
				// 最后一页
				if ((j == 0 && k == count) || i == all)
				{
					values.put("data", data.subList((count-1)*page, all));
					String pager = "<div id='pager'>{count}/{count}页 <a href='"+profix+"_1.html'>首页</a> <a href='"+profix+"_{previous}.html'>上一页</a> 下一页 末页</div>";
					pager = pager.replaceAll("\\{count\\}", String.valueOf(count));
					pager = pager.replaceAll("\\{previous\\}", String.valueOf(count-1));
					values.put("pager", pager);
					this.output(ftl, savepath + profix + "_" + count + ".html", values);
				}
				// 不是最后一页
				else
				{
					if (j == 0)
					{
						values.put("data", data.subList((k-1)*page, (k)*page));
						String pager = "<div id='pager'>{page}/{count}页 <a href='"+profix+"_1.html'>首页</a> <a href='"+profix+"_{previous}.html'>上一页</a> <a href='"+profix+"_{next}.html'>下一页</a> <a href='"+profix+"_{count}.html'>末页</a></div>";
						pager = pager.replaceAll("\\{count\\}", String.valueOf(count));
						pager = pager.replaceAll("\\{page\\}", String.valueOf(k));
						pager = pager.replaceAll("\\{previous\\}", String.valueOf(k-1));
						pager = pager.replaceAll("\\{next\\}", String.valueOf(k+1));
						values.put("pager", pager);
						this.output(ftl, savepath + profix + "_" + k + ".html", values);
					}
				}
			}

		}
	}
}
