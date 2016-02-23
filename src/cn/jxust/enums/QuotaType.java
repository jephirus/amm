package cn.jxust.enums;

import java.util.Map;
import java.util.TreeMap;

public enum QuotaType
{
	FIX("1", "固定指标"), 
	DYNAMIC("2", "动态指标");

	private String index;
	private String text;
	
	private QuotaType(String index, String text)
	{
		this.index = index;
		this.text = text;
	}
	
	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
	
	public static String getText(String index)
	{
		for(QuotaType statue : QuotaType.values())
		{
			if(statue.getIndex() == index)
			{
				return statue.getText();
			}
		}
		return null;
	}
	
	public static String getIndex(String text)
	{
		for(QuotaType statue : QuotaType.values())
		{
			if(statue.getText().equals(text.trim()))
			{
				return statue.getIndex();
			}
		}
		return "";
	}
	
	public static Map<String, String> toMap()
	{
		Map<String, String> map = new TreeMap<String, String>().descendingMap();
		for(QuotaType statue : QuotaType.values())
		{
			map.put(statue.getIndex(), statue.getText());
		}
		return map;
	}
}