package cn.jxust.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 常用加密算法工具类
 * @author yangzhibin
 *
 */
public class EncryptUtils
{

	/**
	 * 用MD5算法进行加密
	 * @param 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String md5(String str)
	{
		return encode(str, "MD5");
	}

	/**
	 * 用SHA算法进行加密
	 * @param 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String sha(String str)
	{
		return encode(str, "SHA");
	}

	/**
	 * 加密算法
	 * @param 需要加密的字符串
	 * @param 加密方法
	 * @return 加密后的字符串
	 */
	private static String encode(String str, String method)
	{
		MessageDigest md = null;
		String dstr = null;
		try
		{
			md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return dstr;
	}

	public static void main(String[] args) throws IOException
	{
		System.out.println("md5加密:"+md5("hello"));
		System.out.println("md5加密:"+md5("中文"));
		
		System.out.println("sha加密:"+sha("hello"));
		System.out.println("sha加密:"+sha("中文"));
		
		String temp = base64Encode("hello");
		System.out.println("base64加密:"+temp);
		System.out.println("base64解密:"+base64Decode(temp));
		
	}
}
