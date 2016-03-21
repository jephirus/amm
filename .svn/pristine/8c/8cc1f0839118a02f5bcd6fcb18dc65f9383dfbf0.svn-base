/**
 * 
 */
package cn.jxust.enums;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jephirus
 *
 */
public enum ConDisClassEnum {

	TD(1,"因土地、山林、水利等问题引发的矛盾纠纷"),
	YH(2,"因医患、交通、意外等非正常伤亡问题引发的矛盾纠纷"),
	HY(3,"因婚姻、家庭、邻里等问题引发的矛盾纠纷"),
	ZD(4,"因征地拆迁、安置补偿等问题引发的矛盾纠纷"),
	GZ(5,"因企业改制、工资福利、社会保险、就业再就业等问题引发的矛盾纠纷"),
	QD(6,"因企地、路地、校地问题引发的矛盾纠纷"),
	ZF(7,"因行政执法活动引发的矛盾纠纷"),
	JJ(8,"因经济活动引发矛盾纠纷"),
	ZZ(9,"因封建宗族和迷信活动等问题引发的矛盾纠纷"),
	WR(10,"因环境污染、安全生产等问题引发的矛盾纠纷"),
	QT(11,"因其它问题引发的矛盾纠纷");
	
	private int index;
	private String text;
	ConDisClassEnum(int index, String text){
		this.index = index;
		this.text = text;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public static String getText(int index)
	{
		for(ConDisClassEnum conDisClass : ConDisClassEnum.values())
		{
			if(conDisClass.getIndex() == index)
			{
				return conDisClass.getText();
			}
		}
		return null;
	}
	
	public static Integer getIndex(String text)
	{
		for(ConDisClassEnum conDisClass : ConDisClassEnum.values())
		{
			if(conDisClass.getText().equals(text.trim()))
			{
				return conDisClass.getIndex();
			}
		}
		return null;
	}
	
	public static Map<Integer, String> toMap()
	{
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for(ConDisClassEnum conDisClass : ConDisClassEnum.values())
		{
			map.put(conDisClass.getIndex(), conDisClass.getText());
		}
		return map;
	}
}
