package cn.jxust.enums;

import java.util.Map;
import java.util.TreeMap;

public enum ScoreState
{
	UNAUDITED("0", "<p class='text-warning'><strong>未审核</strong></p>"),
	PASS("1", "<p class='text-success'><strong>审核通过</strong></p>"), 
	REFUSE("2", "<p class='text-info'><strong>审核拒绝</strong></p>");

	private String index;
	private String text;
	
	private ScoreState(String index, String text)
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
		for(ScoreState statue : ScoreState.values())
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
		for(ScoreState statue : ScoreState.values())
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
		for(ScoreState statue : ScoreState.values())
		{
			map.put(statue.getIndex(), statue.getText());
		}
		return map;
	}
}