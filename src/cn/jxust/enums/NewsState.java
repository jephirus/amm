package cn.jxust.enums;

import java.util.Map;
import java.util.TreeMap;

public enum NewsState
{
	UNAUDITED(0, "未审核"),
	PASS(1, "已审核"), 
	FIXED(2, "不能删除");

	private Integer index;
	private String text;
	
	private NewsState(Integer index, String text)
	{
		this.index = index;
		this.text = text;
	}
	
	public Integer getIndex()
	{
		return index;
	}

	public void setIndex(Integer index)
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
	
	public static String getText(Integer index)
	{
		for(NewsState statue : NewsState.values())
		{
			if(statue.getIndex() == index)
			{
				return statue.getText();
			}
		}
		return null;
	}
	
	public static Integer getIndex(String text)
	{
		for(NewsState statue : NewsState.values())
		{
			if(statue.getText().equals(text.trim()))
			{
				return statue.getIndex();
			}
		}
		return null;
	}
	
	public static Map<Integer, String> toMap()
	{
		Map<Integer, String> map = new TreeMap<Integer, String>().descendingMap();
		for(NewsState statue : NewsState.values())
		{
			map.put(statue.getIndex(), statue.getText());
		}
		return map;
	}
}