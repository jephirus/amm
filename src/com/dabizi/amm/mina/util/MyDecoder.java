package com.dabizi.amm.mina.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import cn.jxust.device.model.ConcentrationMessage;
import cn.jxust.device.model.ExplorerMessage;
import cn.jxust.device.model.SysStateMessage;

import com.dabizi.amm.receiver.GeneralFun;

/**
 * @author lt 自定义解码器，解决断包，粘包问题
 */
public class MyDecoder extends CumulativeProtocolDecoder {

	public static Logger log = Logger.getLogger(MyDecoder.class);
	
	/**
	 * 
	 * 这个方法的返回值是重点：
	 * 
	 * 1、当内容刚好时，返回false，告知父类接收下一批内容
	 * 
	 * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类 CumulativeProtocolDecoder
	 * 
	 * 会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
	 * 
	 * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
	 * 
	 */

	public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		
		log.info("in.remaining : " + in.remaining());
		
		if (in.remaining() > 0) {// 有数据时，读取前8字节判断消息长度(没有读取)
			if (session.getReadBytes()<24) {// 如果消息内容还没结束，则重置，相当于不读取-----数据长度会累加
			//if (in.remaining()<36) {// 如果消息内容还没结束，则重置，相当于不读取
				out.write("error");
				return false;// 父类接收新数据，以拼凑成完整数据
				
			} else {
				
				byte[] msg = new byte[in.limit()]; // 固定2000
				in.get(msg);
				int dataBsm= getUnitBSM(msg);
				List<Object> mi=null;
				/* 判断信息标识码来使用对应的对象并且把直接转换为对应java对象*/
				if(dataBsm==1){
					 mi= DeCoderToJavaObject(msg);
				}else if(dataBsm==2){
					 mi= DeCoderToJavaObject(msg);
				}else if(dataBsm==7){
					mi= DeCoderToJavaObject(msg);
				}else{

					out.write("error");
				}
				
				if(mi.size()>0){
					for (int i = 0; i < mi.size(); i++) {
						Object ms=mi.get(i);
						out.write(ms);
					}
				}else{
					out.write("error");
				}
				
				if (in.remaining() > 0) {// 如果读取内容后还粘了包，就让父类再重读 一次，进行下一次解析
					return true;
				}
			}
		}
		
		return false;// 处理成功，让父类进行接收下个包
	}
	
	
	public String byteToHex(byte[] src, long size) throws Exception {
		String ret = "";
		if (src == null || size <= 0) {
			return null;
		}
		for (int i = 0; i < size; i++) {
			String hex1 = Integer.toHexString(src[i] & 0xFF);

			if (hex1.length() < 2) {
				hex1 = "0" + hex1;
			}
			hex1 += " ";
			ret += hex1;
		}
		return ret.toUpperCase();
	}

	/**
	 * @param message:为二进制数据
	 * 
	 * 将二进制数据解码得到java对象
	 * 
	 * @author lt 
	 * 
	 * @throws IOException
	 */
	private List<Object> DeCoderToJavaObject(byte[] msg) {
		
		//String startChar = ""; // 启动符
		int swiftN = 0; // 流水号
		int version = 0; // 版本号
		String date = ""; // 日期
		String ID = ""; // 信息模块ID
		int dataLen = 0; // 数据单元的长度
		int order = 0; // 命令
		int checkSum = 0; // 校验和
		String dataUnit = ""; // 数据单元
		String endChar = ""; // 结束符
		
		try{
			String str = byteToHex(msg, msg.length);
			str = str.replace(" ", "");

			int flg = -1;
			int amount = -1;
			if (GeneralFun.verifyFun(str.substring(4, 42)).equals(str.substring(42, 44))) {
				String returnStr = "6464" + str.substring(4, 8)
						+ str.substring(8, 12) + str.substring(12, 24)
						+ str.substring(24, 36) + "0000" + GeneralFun.toHex(3);
				returnStr += GeneralFun.verifyFun(returnStr.substring(4, 42)) + "3535";

				swiftN = Integer.parseInt(str.substring(4, 8), 16);//流水号
				version = Integer.parseInt(str.substring(8, 12), 16);//版本号
				date = str.substring(12, 24);//日期
				ID = str.substring(24, 36);//信息模块ID
				dataLen = Integer.parseInt(str.substring(36, 40), 16);//数据长度
				order = Integer.parseInt(str.substring(40, 42), 16);//命令
				//--------以上至此为止控制单元解析并写入Message对象完毕---------
				
				if (dataLen > 0 && order == 2) {
					flg = Integer.parseInt(str.substring(44, 46), 16);// 获取数据单元标志符，1：控制器，2:探测器/外控器，7：带浓度的控测器
					amount = Integer.parseInt(str.substring(46, 48), 16);//数据个数

					switch (flg) {
					case 1:
						/*控制器状态设置*/
						List<Object> sysMessage=new ArrayList<Object>();
						
						int j = 0;
						for (int i = 0; i < amount; i++) {
							SysStateMessage controlStatus=new SysStateMessage();
							controlStatus.setDataCount(amount);
							controlStatus.setDataLength(dataLen);
							controlStatus.setInfoID(ID + str.substring(50+j, 52+j));	// 信息ID+系统地址
							controlStatus.setSerialNum(swiftN);
							controlStatus.setVersionNum(version);
							controlStatus.setCommand(order);
							controlStatus.setTimeLaber(date);
							controlStatus.setDataType(Integer.parseInt(str.substring(48+j, 50+j),16));
							controlStatus.setDataUnitBsm(flg);
							controlStatus.setSysAddress(str.substring(50+j, 52+j));
							
							int high = Integer.parseInt(str.substring(52 + j, 54 + j), 16);
							int low = Integer.parseInt(str.substring(54 + j, 56 + j), 16);
							
							String sstr = "";
							
							for (int k = 0; k < 3; k++) {  // 对 控制器 高字节进行处理
								if ((high & (1 << k)) != 0) {
									switch (k) {
									case 0:
										//sstr += "系统启动";   可不做为故障处理
										break;
									case 2:
										sstr = "系统总线故障";
										break;
									}
								}
							}
							for (int l = 0; l < 8; l++) {  // 对 控制器低字节进行处理
								if ((low & (1 << l)) != 0) {
									switch (l) {
									case 0:
										//sstr += "正常"; 正常不做为故障
										break;
									case 5:
										sstr = "备电连线故障";  // 后增，2015.11.23
										break;
									case 6:
										sstr = "主电欠压";
										break;
									case 7:
										sstr = "备电欠压";
										break;
									}
								}
							}
							controlStatus.setControllerStatus(sstr);
							sysMessage.add(controlStatus);
							j += 8;
						}
						return sysMessage;
					case 2:
						int jd = 0;
						List<Object> exMessage=new ArrayList<Object>();
						for (int i = 0; i < amount; i++) {
							ExplorerMessage exstatus=new ExplorerMessage();
							exstatus.setInfoID(ID + str.substring(50 + jd, 52 + jd));	// 信息ID+系统地址
							exstatus.setCommand(order);
							exstatus.setDataCount(amount);
							exstatus.setDataLength(dataLen);
							exstatus.setSerialNum(swiftN);
							exstatus.setVersionNum(version);
							exstatus.setTimeLaber(date);
							exstatus.setDataType(Integer.parseInt(str.substring(48 + jd, 50 + jd), 16));
							exstatus.setDataUnitBsm(flg);
							exstatus.setSysAddress(str.substring(50 + jd, 52 + jd));
							exstatus.setDeviceType(Integer.parseInt(str.substring(52 + jd, 54 + jd), 16));
							exstatus.setDeviceAddress(str.substring(54 + jd, 62 + jd));
							
							int low = Integer.parseInt(str.substring(64 + jd, 66 + jd), 16);
							
							int type = Integer.parseInt(str.substring(52 + jd, 54 + jd), 16);  // 判断是否为探测器（11）还是外控器（86）
							
							String sstr = "";
							if (type == 11) {   // 是探测器
								for (int k = 0; k < 3; k++) {
									if ((low & (1 << k)) != 0) {
										switch (k) {
										case 0:
											sstr = "屏蔽";
											break;
										case 1:
											sstr = "连线故障";
											break;
										case 2:
											sstr = "探测器故障";
											break;
										}
									}
								}
 	    					} else if (type == 86){    // 外控制器
 	    						for(int k = 0; k < 4; k++){
 	    							if((low & (1 << k)) != 0){
 	    								switch(k){
 	    								case 0:
 	    									sstr = "屏蔽" ;
     	    								break ;
 	    								case 1:
 	    									sstr = "连线故障" ;
 	    									break ;
 	    								case 2:
 	    									sstr = "低开关量" ;
 	    									break ;
 	    								case 3:
 	    									sstr = "高开关量" ;
 	    									break ;
     	    							}
     	    						}
     	    					}
							}
							exstatus.setDeviceStatus(sstr);
							exMessage.add(exstatus);
							jd += 18;
						}
						return exMessage;
					case 7:
						List<Object> conMessage=new ArrayList<Object>();
						int jv = 0;
						for (int i = 0; i < amount; i++) {
							ConcentrationMessage valueMessage=new ConcentrationMessage();
							valueMessage.setCommand(order);
							valueMessage.setDataLength(dataLen);
							valueMessage.setInfoID(ID + str.substring(50 + jv, 52 + jv));	// 信息ID+系统地址
							valueMessage.setSerialNum(swiftN);
							valueMessage.setVersionNum(version);
							valueMessage.setTimeLaber(date);
							valueMessage.setDataCount(amount);
							valueMessage.setDataType(Integer.parseInt(str.substring(48 + jv,50 + jv), 16));
							valueMessage.setDataUnitBsm(flg);
							valueMessage.setDeviceAddress(str.substring(54 + jv, 62 + jv));
							valueMessage.setSysAddress(str.substring(50 + jv, 52 + jv));
							valueMessage.setDeviceType(Integer.parseInt(str.substring(52 + jv,54 + jv), 16));
							
							int low = Integer.parseInt(str.substring(66 + jv, 68 + jv), 16);
							String sstr = "";   // 故障状态
							int bit0 = 0, bit4 = 0, bit5 = 0, bit6 = 0;  // bit4bit0表示小数点；bit6bit5表示表位
							if (low == 238) {
								sstr = "连线故障";
							} else
								for (int k = 0; k < 7; k++) {
									if ((low & (1 << k)) != 0) {
										switch (k) {
										case 0:
											bit0 = 1;
											break;
										case 1:
											sstr = "低限报警";
											break;
										case 2:
											sstr = "高限报警";
											break;
										case 3:
											sstr = "探测器故障";
											break;
										case 4:
											bit4 = 1;
											break;
										case 5:
											bit5 = 1;
											break;
										case 6:
											bit6 = 1;
											break;
										}
									} else {
										switch (k) {
										case 0:
											bit0 = 0;
											break;
										case 4:
											bit4 = 0;
											break;
										case 5:
											bit5 = 0;
											break;
										case 6:
											bit6 = 0;
											break;
										}
									}
								}
							String unit = "";
							float scale = 0;
							String sss = "" + bit4 + bit0;
							if (sss.equals("00"))
								scale = 1;
							else if (sss.equals("01"))
								scale = 10;
							else if (sss.equals("10"))
								scale = 100;
							else
								scale = 1000;

							sss = "" + bit6 + bit5;
							if (sss.equals("00"))
								unit = "LEL";
							else if (sss.equals("01"))
								unit = "PPM";
							else if (sss.equals("10"))
								unit = "VOL";
							else
								unit = "PPB";

									valueMessage.setStatus(sstr);
									valueMessage.setSimulation(Integer.parseInt(str.substring(62 + jv,66 + jv)) / scale + "%" + unit);
									conMessage.add(valueMessage);
									jv += 20;
						
						}
						return conMessage;
					}
				}
			}
			return null;

		} catch (Exception e) {
			System.out.println("数据解码异常");
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Description:获取数据单元标识符（1，2，7）
	 * @author:laiy
	 * @date:2015年10月13日  下午9:12:25 
	 * @param: @param msg
	 * @param: @return  
	 * @return: int
	 * @throws
	 */
	public int getUnitBSM(byte[] msg)throws Exception{
		
		String str = byteToHex(msg, msg.length);
		str = str.replace(" ", "");
		int dataLen = Integer.parseInt(str.substring(36, 40), 16);//数据长度
		int order = Integer.parseInt(str.substring(40, 42), 16);//命令
		int flg=-1;
		if (GeneralFun.verifyFun(str.substring(4, 42)).equals(
				str.substring(42, 44))) {
			if (dataLen > 0 && order == 2) {
				flg = Integer.parseInt(str.substring(44, 46), 16);//获取数据单元标志符，1，2，7
				
			}
		}
		return flg;
	}
}
