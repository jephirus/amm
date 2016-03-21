package cn.jxust.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.jxust.base.model.Config;
import cn.jxust.base.service.ConfigService;

public class ConfigUtils
{
	private Map<String, String> values;
	private Set<String> keys;
	
	private List<Config> list = new ArrayList<Config>();
	
	private static ConfigUtils util;
	
	/**
	 * 单例设计模式
	 */
	private ConfigUtils(){}
	
	private void setValues()
	{
		values = new HashMap<String, String>();
		for (Config config : list)
		{
			values.put(config.getConfigKey(), config.getConfigValue());
		}
	}

	private void setKeys()
	{
		if (null == values)
		{
			setValues();
		}
		keys = values.keySet();
	}

	public Map<String, String> getValues()
	{
		if (null == values)
		{
			setValues();
		}
		return values;
	}

	public Set<String> getKeys()
	{
		if (null == keys)
		{
			setKeys();
		}
		return keys;
	}
	
	public String getValueByKey(String key)
	{
		if (null == values)
		{
			setValues();
		}
		return values.get(key);
	}
	
	/**
	 * 刷新ConfigUtils
	 */
	public synchronized static void refreshConfigUtils()
	{
		ConfigService service = (ConfigService)SpringContextUtils.getBean("configService");
		util.list = service.getAll();
		
		util.setKeys();
		util.setValues();
		
		service = null;
	}
	
	/**
	 * 单例设计模式
	 * @return
	 */
	public synchronized static ConfigUtils getInstance()
	{
		if(null == util)
		{
			util = new ConfigUtils();
			refreshConfigUtils();
		}
		return util;
	}
}
