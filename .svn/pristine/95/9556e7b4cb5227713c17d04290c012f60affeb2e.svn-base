package cn.jxust.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils
{
	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	
	private static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	
	private static SimpleDateFormat nowFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	//获得当前年份
	public static String getCurrentYear()
	{
		return yearFormat.format(new Date());
	}
	
	//获得当前月份
	public static String getCurrentMonth()
	{
		return monthFormat.format(new Date());
	}
	
	//获得上个月份
	public static String getLastMonth()
	{
		Calendar a = Calendar.getInstance();
		a.setTime(new Date());
		a.set(Calendar.DAY_OF_MONTH, -1);
		return monthFormat.format(a.getTime());
	}
	
	//获得当前时间 精确到分钟
	public static String getCurrentTime()
	{
		return nowFormat.format(new Date());
	}
	
	//计算截至提交时间数组 0：提交月份 1：提交剩余天数
	public static String[] getDaysForSubmit()
	{
		String[] result = new String[2];
		//1.获得提交日期
		String submitDay = ConfigUtils.getInstance().getValues().get("submit_time");
		int sDay = Integer.parseInt(submitDay);
		//2.获得当前天
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_MONTH);
		//逻辑判断
		if(today > Integer.parseInt(submitDay))
		{
			//返回当月的天数
			Calendar a = Calendar.getInstance();
			a.set(Calendar.DATE, 1);//把日期设置为当月第一天
			a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
			int maxDate = a.get(Calendar.DATE);
			result[0] = getCurrentMonth();
			result[1] = String.valueOf(maxDate - today + sDay);
		}
		else
		{
			//上个月
			Calendar a = Calendar.getInstance();
			a.set(Calendar.MONTH, a.get(Calendar.MONTH)-1);
			result[0] = monthFormat.format(a.getTime());
			result[1] = String.valueOf(sDay - today);
		}
		return result;
	}
	
	public static List<String> getYearMonth(String year)
	{
		if(null == year || "".equals(year.trim()))
		{
			year = yearFormat.format(new Date());
		}
		List<String> yearMonth = new ArrayList<String>();
		
		int currentYear = Integer.parseInt(year);
		int lastYear = Integer.parseInt(year) - 1;
		
		for(int j=currentYear ; j>=lastYear ; j--)
		{
			for(int i=12 ; i>=1 ; i--)
			{
				yearMonth.add(j + "-" + String.format("%02d", i));
			}
		}
		
		return yearMonth;
	}
	
	public static List<String> getPerMonthByCuttentYear()
	{
		String year = getCurrentYear();
//		String year = "2013";
		Calendar a = Calendar.getInstance();
		int month = a.get(Calendar.MONTH)+1;
		List<String> months = new ArrayList<String>();
		for(int i=month ; i>0 ; i--)
		{
//			if(i!=8 && i!=9 && i!=10 && i!=11)
				months.add(year + "-" + String.format("%02d", i));
		}
		return months;
	}
	
	public static List<String> getYearList(int size)
	{
		String currentYear = DateUtils.getCurrentYear();
		int year = Integer.parseInt(currentYear);
		List<String> years = new ArrayList<String>();
		for(int i=year-size ; i<=year+size ; i++)
		{
			years.add(String.valueOf(i));
		}
		return years;
	}
	
	public static List<Object> getMonthListByCurrentMonth()
	{
		String currentYear = DateUtils.getCurrentYear();
		int year = Integer.parseInt(currentYear);
		
		List<Object> years = new ArrayList<Object>();
		
		for(int j=year ; j>=year-1; j--)
		{
			for(int k=12 ; k>0 ; k--)
			{
				years.add(j + "-" + String.format("%02d", k));
			}
		}
		
		return years;
	}
	
	public static List<String> getYearsStart2013()
	{
		String currentYear = DateUtils.getCurrentYear();
		int year = Integer.parseInt(currentYear);
		
		List<String> years = new ArrayList<String>();
		for(int i=2013 ; i<=year ; i++)
		{
			years.add(String.valueOf(i));
		}
		return years;
	}
	
	public static void main(String[] args)
	{
//		String[] a = getDaysForSubmit();
//		System.out.println(a[0]);
//		System.out.println(a[1]);
		System.out.println(DateUtils.getLastMonth());
//		for(String s: getYearMonth(null))
//		{
//			System.out.println(s);
//		}
	}
}
