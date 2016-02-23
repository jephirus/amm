package cn.jxust.enums;

import java.util.Map;
import java.util.TreeMap;

public enum SysDataType
{
	FIXEDQUOTADATA("1", "固定指标数据"),
	DEPARTMENTFILE("2", "单位文件"), 
	ACTIVEPIC("3", "活动图片"),
	SYSDATAMATERIAL("4", "综合材料"),
	
	PLAN("5","工作部署"),
	DEVELOP("6","工作开展"),
	EFFECT("7","工作效果")
	;
	

	private String index;
	private String text;
	
	private SysDataType(String index, String text)
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
		for(SysDataType statue : SysDataType.values())
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
		for(SysDataType statue : SysDataType.values())
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
		for(SysDataType statue : SysDataType.values())
		{
			map.put(statue.getIndex(), statue.getText());
		}
		return map;
	}
}