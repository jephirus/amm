package cn.jxust.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.jxust.base.service.UserService;

public class UserUtils
{
	@Resource
	public UserService userService;

	/**
	 * iso8859-1转化为utf-8编码
	 * @param str
	 * @return
	 */
	public static String iso2utf(String str)
	{
		String result = StringUtils.stripToEmpty(str);
		try
		{
			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getCurrentUserName(){
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		try {
			username = URLEncoder.encode(username,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return username;
		
	}
	
}
