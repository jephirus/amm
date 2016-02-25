package com.dabizi.amm.uegateSoapInterfaceAxis;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import cn.jxust.sms.model.SMSInfo;

/**
 * @author lt
 * @crateTime 2015-7-10 上午10:37:19
 */
public class CallUtils {

	// 以下信息，根据自己的账户信息修改
	public static final String spID = "000082";
	public static final String password = "82891770";
	public static final String accessCode = "1069032239089";

	// 默认发送的短信内容
	public static final String defaultContent = "探测器报警";
	// 默认发送的短信内容模版
	public static final String CONTENT_MOBAN = "%s";
	// 默认手机号码串
	public static final String defaultNumber = "15779001007";

	/**
	 * 向默认的一些号码发送短信，你可以不用指定手机号吗，发送默认的短信内容
	 * 
	 * @return 信息发送返回的信息对象
	 * @author lt
	 */
	public static SMSInfo sendMsg() {
		return sendMsg(defaultContent,defaultNumber);
	}

	/**
	 * 向指定手机号码发送短信
	 * 
	 * @param content
	 *            发送短信的内容
	 * @param mobileString
	 *            最大号码个数10000 号码之间以英文逗号分割，如：1380000001, 1380000002
	 * @return 返回发送消息返回的字符串
	 * @author lt
	 */
	public static SMSInfo sendMsg(String content, String mobileString) {
		if(content.isEmpty()&&mobileString.isEmpty()){ // 非法参数
			throw new ArithmeticException("参数不能为空");
		}
		// 短信提交
		UegateSoap uegatesoap = new UegateSoap();
		String submitresult = uegatesoap.Submit(spID, password, accessCode,
				content, mobileString);
		submitresult = submitresult.split("\n")[0];
		String resultCode = (String) submitresult.subSequence(0, submitresult.indexOf("#@#"));
		String surplusStr = (String) submitresult.subSequence(submitresult.indexOf("#@#")+3, submitresult.lastIndexOf("#@#"));
		SMSInfo smsInfo = new SMSInfo();
		smsInfo.postime = getCurrentTime();
		if ("0".equals(resultCode)) {//如果返回码是0  表示发送成功
			Float surplusMoney = Float.parseFloat(surplusStr) / 1000; // 剩余多少钱（元）
			Integer surplusNumber = Integer.parseInt(surplusStr) / 80; // 剩余多少条短信
			smsInfo.status = SMSInfo.SEND_SUCCESS;																// （条）
			smsInfo.surplusMoney = surplusMoney;
			smsInfo.surplusNumber = surplusNumber;
		}else{ // 发送失败
			smsInfo.status = SMSInfo.SEND_FAIL;
		}
		return smsInfo;
	}

	/**
	 *@return YYYY-MM-dd HH:mm:ss格式的时间
	 *@author lt
	 */
	private static String getCurrentTime() {
		SimpleDateFormat  format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 查询余额
	 * 
	 * @return 账户余额
	 * @author lt
	 */
	public static String QueryMo() {
		// 查询余额
		UegateSoap uegatesoap = new UegateSoap();
		String querymoresult = uegatesoap.QueryMo(spID, password);
		System.out.println("querymoresult:" + querymoresult);
		return querymoresult;
	}

	@Test
	public void test() {
//		submitresult:0#@#4600#@#
//		7C4E10C3827215A3#@#15779707988#@#0#@#
//		7C4E10C3827215A4#@#15779001700#@#0#@#
		String test = "0#@#5500#@#\n7C4E10C3827215A3#@#15779707988#@#0#@#\n"+
			"7C4E10C3827215A4#@#15779001700#@#0#@#";
		String resultCode = (String) test.subSequence(0, test.indexOf("#@#"));
		test = test.replace("#@#", "tag");
		System.out.println(test.indexOf("#@#"));
		//String surplusStr = (String) test.subSequence(test.indexOf("tag"), test.indexOf("#@#"));
		System.out.println(resultCode);
		//System.out.println(surplusStr);
		int num = test.split("\n").length-1;
		for(int i=0;i<num;i++){
			
		}
		//System.out.println("num=="+num);
		/*if ("0".equals(resultCode)) {//如果返回码是0  表示发送成功
			Float surplusMoney = Float.parseFloat(surplusStr) / 1000; // 剩余多少钱（元）
			Long surplusNumber = Long.parseLong(surplusStr) / 80; // 剩余多少条短信
																				// （条）
			System.out.println("resultCode=" + resultCode);
			System.out.println("surplusMoney=" + surplusMoney);
			System.out.println("surplusNumber=" + surplusNumber);
		}*/
	}
	

	/**
	 * 短信回复
	 * 
	 * @return 短信回复的内容
	 * @author lt
	 */
	public static String RetrieveAll() {
		// 短信回复
		UegateSoap uegatesoap = new UegateSoap();
		String receiveresult = uegatesoap.RetrieveAll(spID, password);
		System.out.println("receiveresult:" + receiveresult);
		return receiveresult;
	}
}
