package com.dabizi.amm.receiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import cn.jxust.base.model.User;
import cn.jxust.device.model.AlarmParameter;
import cn.jxust.device.model.AttachDevice;
import cn.jxust.device.model.ConcentrationMessage;
import cn.jxust.device.model.Device;
import cn.jxust.device.model.ExplorerMessage;
import cn.jxust.device.model.Prober;
import cn.jxust.device.model.SysStateMessage;
import cn.jxust.device.service.AlarmParameterService;
import cn.jxust.device.service.AttachDeviceService;
import cn.jxust.device.service.ConcentrationMessageService;
import cn.jxust.device.service.DeviceService;
import cn.jxust.device.service.ExplorerMessageService;
import cn.jxust.device.service.MessageService;
import cn.jxust.device.service.ProberService;
import cn.jxust.device.service.SysStateMessageService;

import com.dabizi.amm.uegateSoapInterfaceAxis.CallUtils;
import com.dabizi.amm.websocket.SystemWebSocketHandler;
import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

/**
 * 负责处理连接上来的客户机，即消息处理器
 */
@Component
public class AlarmMessageHandler extends IoHandlerAdapter {

	@Resource
	private MessageService messageService;
	
	@Resource
	private PointInfoService pointInfoService;
	
	@Resource
	private SysStateMessageService sysStateMessageService;
	
	@Resource
	private ExplorerMessageService explorerMessageService;
	
	@Resource
	private ConcentrationMessageService concentrationMessageService;

	@Resource
	private DeviceService deviceService;

	@Resource
	private ProberService proberService;

	@Resource
	private AttachDeviceService attachDeviceService;

	@Resource
	private AlarmParameterService alarmParameterService;

	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	/**
	 * Set<Device> errorDevices：存储上一个报警设备的地址，这时不管设备是控制器还是探测器或外控器，都可以存放
	 * 目的是如果第一次报警，放进该集合，并发短信； 如果有下一条报警消息，并且是当前设备，要判断该集合是否已存该消息；
	 * 如果存在不再添加，否则添加进该集合。
	 */
	private static Map<String, String[]> errorDevices = new HashMap<String, String[]>();
	private static Map<String, String[]> earlyWarningDevices = new HashMap<String, String[]>();  // 预警值标志,0为<10, 1为>10

	/**
	 * 当消息到达的时候调用，这里接收的message已经被累加过滤器封装成一个对象了 这里只要处理业务逻辑
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
	{
		
		SysStateMessage sysMessage = null;
		ExplorerMessage explorerMessage = null;
		ConcentrationMessage conMessage = null;
		String timeLaber = null;
		PointInfo pointInfo = null;

		/* 判断是哪一个类 */
		if (message instanceof SysStateMessage) {
			sysMessage = (SysStateMessage) message;
			timeLaber = sysMessage.getTimeLaber();
		} else if (message instanceof ExplorerMessage) {
			explorerMessage = (ExplorerMessage) message;
			timeLaber = explorerMessage.getTimeLaber();
		} else if (message instanceof ConcentrationMessage) {
			conMessage = (ConcentrationMessage) message;
			timeLaber = conMessage.getTimeLaber();
		}
		// 将报文信息存入数据库

		StringBuffer sb = new StringBuffer();
		//System.out.println("time:"+timeLaber);
		sb.append(timeLaber.substring(0, 2) + "年");
		sb.append(timeLaber.substring(2, 4) + "月");
		sb.append(timeLaber.substring(4, 6) + "日");
		sb.append(timeLaber.substring(6, 8) + "时");
		sb.append(timeLaber.substring(8, 10) + "分");
		sb.append(timeLaber.substring(10, 12) + "秒");
		timeLaber = sb.toString();
		
		if (sysMessage != null) {
			sysMessage.setTimeLaber(timeLaber);
			sysStateMessageService.save(sysMessage);
		} else if (explorerMessage != null)
		{
			explorerMessage.setTimeLaber(timeLaber);
			explorerMessageService.save(explorerMessage);
		} else if (conMessage != null) {
			conMessage.setTimeLaber(timeLaber);
			try {
				concentrationMessageService.save(conMessage);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		synchronized (this) {
			
			if (sysMessage != null)//控制器消息
			{
				haddleDevice(sysMessage, pointInfo);
			}
			else if (explorerMessage != null) // 探测器或外控器消息
			{
				haddleProber(explorerMessage, pointInfo, timeLaber);
			}
			else if (conMessage != null)   // 带浓度的探测器
			{
				this.handleProberWithConcentration(conMessage, pointInfo, timeLaber);
			}
		}
		session.write("发送成功");
	}

	/**
	 * 当发生故障或报警时，发送短信
	 * @param message
	 * @param device
	 * @param pointInfo
	 */
	private void sendFAShortMessage(Object message, Device device, PointInfo pointInfo)
	{

		StringBuffer msgSb = new StringBuffer();
		String[] flagAndStat = {"0", "0", "0"};  // 下标0为设备类型； 下标1为状态；下标2为设备类型为7时，“1”为报警“2”为故障.
		String sessionName = "";
		/* 判断是哪一个类 */
		if (message instanceof SysStateMessage) {
			// 拼凑短信内容，控制器触发,格式：所属单位+区域+控制器+时间+原因
			SysStateMessage sysMessage = (SysStateMessage) message;
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "于");
			msgSb.append(sysMessage.getTimeLaber() + "发生");
			msgSb.append(sysMessage.getControllerStatus() + "【东震】");
			flagAndStat[1] = sysMessage.getControllerStatus();
			flagAndStat[2] = "2";  // 故障
			errorDevices.put(device.getDeviceCode(), flagAndStat); // 添加，如果有就不会添加，set特性
		} else if (message instanceof ExplorerMessage) {
			ExplorerMessage explorerMessage = (ExplorerMessage) message;
			int type = explorerMessage.getDeviceType();  // 判断是否是探测器或外控器地址。
			// 拼凑短信内容，控制器触发,格式：所属单位+区域+控制器+时间+原因
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "于");
			msgSb.append(explorerMessage.getTimeLaber() + "发生");
			msgSb.append(explorerMessage.getDeviceStatus() + "【东震】");
			if (type == 11) {
				flagAndStat[0] = "2";  // 不带浓度探测器
				flagAndStat[1] = explorerMessage.getDeviceStatus();
				flagAndStat[2] = "2";  // 故障
				errorDevices.put("11" + device.getDeviceCode() + explorerMessage.getDeviceAddress(), flagAndStat); // 添加，如果有就不会添加，set特性
			}else{
				flagAndStat[1] = explorerMessage.getDeviceStatus();
				flagAndStat[2] = "2";  // 故障
				errorDevices.put("86" + device.getDeviceCode() + explorerMessage.getDeviceAddress(), flagAndStat); // 添加，如果有就不会添加，set特性
			}
		} else if (message instanceof ConcentrationMessage) {
			ConcentrationMessage conMessage = (ConcentrationMessage) message;
			// 拼凑短信内容，格式：所属单位+区域+控制器+时间+原因
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "的");
			msgSb.append(conMessage.getDeviceAddress() + "探测器于"); // 探测器地址
			msgSb.append(conMessage.getTimeLaber() + "发生");
			msgSb.append(conMessage.getStatus() + "【东震】");
			flagAndStat[0] = "7";  // 带浓度探测器
			if(conMessage.getStatus().equals("连线故障") || conMessage.getStatus().equals("探测器故障")){
				flagAndStat[2] = "2";  // 故障
				flagAndStat[1] = conMessage.getStatus();
			}
			else
			{
				flagAndStat[2] = "1";  // 报警
				flagAndStat[1] = conMessage.getStatus();
			}
			errorDevices.put("11" + device.getDeviceCode() + conMessage.getDeviceAddress(), flagAndStat); // 添加，如果有就不会添加，set特性
		}
		// 发短信
		String phoneNumbers = "";
		Set<User> us = device.getDeviceManagers();
		for (User user : us) {// 给多个发短信
			if (null != user.getPhoneNumber()) {
				phoneNumbers += user.getPhoneNumber() + ",";
			}
		}
		if (null != pointInfo) {
			pointInfo.setStatus(PointInfo.STATUS_ERROR);
			if (flagAndStat[2].equals("1")) {
				pointInfo.setStatusColor("red");
			}else if(flagAndStat[2].equals("2"))
			{
				pointInfo.setStatusColor("yellow");
			}
			sessionName = pointInfo.getLatitude() + "|" + pointInfo.getLongitude();
			pointInfoService.update(pointInfo);
		}
		phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
		CallUtils.sendMsg(msgSb.toString(), phoneNumbers);
		systemWebSocketHandler.sendMessageToIndex(new TextMessage(msgSb.toString() +"*#*" + sessionName +"*#*" + flagAndStat[2]+"*#*" + device.getArea().getAreaId()));
	}

	/**
	 * 当故障、报警恢复时，发送短信
	 * @param message
	 * @param device
	 * @param pointInfo
	 */
	private void sendRecoverShortMessage(Object message, Device device)
	{

		StringBuffer msgSb = new StringBuffer();
		/* 判断是哪一个类 */
		if (message instanceof SysStateMessage) {
			// 拼凑短信内容，控制器触发,格式：所属单位+区域+控制器+时间
			SysStateMessage sysMessage = (SysStateMessage) message;
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "于");
			msgSb.append(sysMessage.getTimeLaber() + "恢复正常。【东震】");
		} else if (message instanceof ExplorerMessage) {
			ExplorerMessage explorerMessage = (ExplorerMessage) message;
			// 拼凑短信内容，控制器触发,格式：所属单位+区域+控制器+时间
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "于");
			msgSb.append(explorerMessage.getTimeLaber() + "恢复正常。【东震】");
		} else if (message instanceof ConcentrationMessage) {
			ConcentrationMessage conMessage = (ConcentrationMessage) message;
			// 拼凑短信内容，格式：所属单位+区域+控制器+时间
			msgSb.append(device.getArea().getDepartment().getDepartmentName());
			msgSb.append(device.getArea().getAreaName());
			msgSb.append(device.getDeviceName() + "的");
			msgSb.append(conMessage.getDeviceAddress() + "探测器于"); // 探测器地址
			msgSb.append(conMessage.getTimeLaber() + "恢复正常。【东震】");
		}
		// 发短信
		String phoneNumbers = "";
		Set<User> us = device.getDeviceManagers();
		for (User user : us) {// 给多个发短信
			if (null != user.getPhoneNumber()) {
				phoneNumbers += user.getPhoneNumber() + ",";
			}
		}
		phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
		CallUtils.sendMsg(msgSb.toString(), phoneNumbers);
	}

	/**
	 * 用于处理控制器消息。
	 * @param sysMessage 控制器消息
	 * @param pointInfo 地图结点
	 */
	private void haddleDevice(SysStateMessage sysMessage, PointInfo pointInfo){
		
		String address = sysMessage.getInfoID() + sysMessage.getSysAddress(); // 此外为控制器地址
		Device device = deviceService.findByDeviceCode(address); // 获取控制器
		String status = sysMessage.getControllerStatus(); // 获取控制器状态
		
		if (null != device)    // 如果存在该控制器
		{ 
			pointInfo = device.getPointInfo(); // 获取地图结点
			if (status.equals(""))   // 控制器正常
			{
				if (errorDevices.containsKey(address))
				{
					device.setStatus("<td style=\"color:#009900\">正常</td>");
					deviceService.update(device);
					errorDevices.remove(address); // 正常了，从Map中移除错误地址
				}
				if (null != pointInfo) {
					pointInfo.setStatus(PointInfo.STATUS_OK); // 更新点的信息
					pointInfo.setStatusColor("green");
					pointInfoService.update(pointInfo);
				}
			} 
			else    // 控制器状态不正常
			{
				if (!errorDevices.containsKey(address) || !(errorDevices.get(address)[1].equals(status)))   // 该设备第一次发送故障信息，发送一次短信
				{
					device.setStatus(this.statusValueAndColor(status));
					deviceService.update(device);
					sendFAShortMessage(sysMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
				}
			}
		}
	}

	/**
	 * 用于处理探测器或外控器发送来的消息
	 * @param explorerMessage
	 * @param pointInfo
	 * @param timeLaber 消息发生时间
	 */
	private void haddleProber(ExplorerMessage explorerMessage, PointInfo pointInfo, String timeLaber)
	{
		Device device = deviceService.findByDeviceCode(explorerMessage.getInfoID() + explorerMessage.getSysAddress());
		int type = explorerMessage.getDeviceType();  // 判断是否是探测器或外控器地址。
		String address; // 探测器或外控器地址：控制器地址+探或外地址
		if (type == 11) {
			address = "11" + device.getDeviceCode() + explorerMessage.getDeviceAddress();
		}
		else
		{
			address = "86" + device.getDeviceCode() + explorerMessage.getDeviceAddress();
		}
		String status = explorerMessage.getDeviceStatus();   // 控测器或外控器的当前状态
		if (null != device) // 如果存在该设备
		{
			pointInfo = device.getPointInfo();
			if (status.equals("") && errorDevices.containsKey(address))   // 状态正常同时此前没有故障或报警
			{
				if (type == 11 && errorDevices.get(address)[0].equals("2"))    // 探测器故障
				{
					device.setProberFaultCount(device.getProberFaultCount() -1);// 探测器故障解除，恢复为0.
					for (Prober p: device.getProbers())
					{
						if (p.getLocation().equals(explorerMessage.getDeviceAddress()))
						{
							p.setCurrentStatus(this.recoveryStatusValue(errorDevices.get(address)[1]));
							p.setAlarmTime(timeColor(status, timeLaber));
							proberService.update(p);
						}
					}
				}else if (type == 11 && errorDevices.get(address)[0].equals("7"))    // 探测器带浓度报警
				{
					device.setProberAlarmCount(device.getProberAlarmCount() -1);// 探测器故障解除，恢复为0.
					for (Prober p : device.getProbers())
					{
						if (p.getLocation().equals(explorerMessage.getDeviceAddress()))
						{
							p.setCurrentStatus(this.recoveryStatusValue(errorDevices.get(address)[1]));
							p.setAlarmTime(timeColor(status, timeLaber));
							proberService.update(p);
						}
					}
				}
				else if (type == 86)  // 外控器
				{
					device.setAttachDeviceFaultCount(device.getAttachDeviceFaultCount() -1);// 外控器故障解除，恢复为0.
					for (AttachDevice ad : device.getAttachDevices())
					{
						if (ad.getLocation().equals(explorerMessage.getDeviceAddress()))
						{
							ad.setCurrentStatus(this.recoveryStatusValue(errorDevices.get(address)[1]));
							ad.setAlarmTime(timeColor(status, timeLaber));
							attachDeviceService.update(ad);
						}
					}
				}
				deviceService.update(device);
				if (null != pointInfo) {
					pointInfo.setStatus(PointInfo.STATUS_OK);
					pointInfo.setStatusColor("green");
					pointInfoService.update(pointInfo);
				}
				errorDevices.remove(address);
			}
			else // 不正常
			{
				if (!errorDevices.containsKey(address)) // 该设备第一次发送故障信息，发送一次短信
				{
					if (type == 11)    // 探测器
					{
						device.setProberFaultCount(device.getProberFaultCount() + 1);// 探测器故障发生次数递增1
						for (Prober p : device.getProbers()) {
							if (p.getLocation().equals(explorerMessage.getDeviceAddress())) {
								p.setCurrentStatus(this.statusValueAndColor(status));
								p.setCurrentThickness("0");   // 探测器有故障，当前浓度设为0.
								p.setAlarmTime(timeColor(status, timeLaber));
								proberService.update(p);
							}
						}
					}
					else if (type == 86)  // 外控器
					{
						device.setAttachDeviceFaultCount(device.getAttachDeviceFaultCount() + 1);// 外控器故障次数递增1
						for (AttachDevice ad : device.getAttachDevices())
						{
							if (ad.getLocation().equals(explorerMessage.getDeviceAddress()))
							{
								ad.setCurrentStatus(this.statusValueAndColor(status));
								ad.setAlarmTime(timeColor(status, timeLaber));
								attachDeviceService.update(ad);
							}
						}
					}
					deviceService.update(device);
					sendFAShortMessage(explorerMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信 （一次故障只发一次短信）
				}
				else  // 非第一次报警，
				{
					if (type == 11) // 探测器或带浓度的探测器
					{
						if (errorDevices.get(address)[0].equals("2") && !errorDevices.get(address)[1].equals(status))  // 探测器
						{
							for (Prober p : device.getProbers())
							{
								if (p.getLocation().equals(explorerMessage.getDeviceAddress()))
								{
									p.setCurrentStatus(statusValueAndColor(status));
									proberService.update(p);
								}
							}
							sendFAShortMessage(explorerMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信 （一次故障只发一次短信）
						}
						else if(errorDevices.get(address)[0].equals("7")) // 上次是带浓度的探测器报警，现在探测器发生故障，探测报警取消-1，故障+1
						{
							device.setProberAlarmCount(device.getProberAlarmCount() - 1);  // 探测器发生故障，报警取消-1.
							device.setProberFaultCount(device.getProberFaultCount() + 1); // 探测器故障发生次数+1
							for (Prober p : device.getProbers())
							{
								if (p.getLocation().equals(explorerMessage.getDeviceAddress()))
								{
									p.setCurrentStatus(statusValueAndColor(status));
									p.setCurrentThickness("0");   // 探测器有故障，当前浓度设为0.
									proberService.update(p);
								}
							}
							deviceService.update(device);
							sendFAShortMessage(explorerMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信 （一次故障只发一次短信）
						}
					} 
					else if (type == 86 && !errorDevices.get(address)[1].equals(status)) // 外控器
					{
						for (AttachDevice ad : device.getAttachDevices()) {
							if (ad.getLocation().equals(explorerMessage.getDeviceAddress()))
							{
								ad.setCurrentStatus(statusValueAndColor(status));
								attachDeviceService.update(ad);
							}
						}
						sendFAShortMessage(explorerMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信 （一次故障只发一次短信）
					}
				}
			}
		}
		
	}

	/**
	 * 处理带浓度的探测器
	 * @param conMessage
	 * @param pointInfo
	 * @param timeLaber
	 */
	private void handleProberWithConcentration(ConcentrationMessage conMessage, PointInfo pointInfo, String timeLaber)
	{
		
		Device device = deviceService.findByDeviceCode(conMessage.getInfoID() + conMessage.getSysAddress());
		String address ="11" + device.getDeviceCode() + conMessage.getDeviceAddress(); // 此地址为该控制器下的探测器地址：控制器地址+探测器地址
		if (null != device) // 如果存在该设备
		{
			pointInfo = device.getPointInfo();
			String status = conMessage.getStatus();

			if (status.equals(""))   // 状态正常
			{
				if (errorDevices.containsKey(address)) // 之前有报警，现在恢复正常
				{
					if (null != pointInfo)
					{
						pointInfo.setStatus(PointInfo.STATUS_OK);
						pointInfo.setStatusColor("green");
						pointInfoService.update(pointInfo);
					}
					if (errorDevices.get(address)[0].equals("7"))
					{
						if (errorDevices.get(address)[2].equals("1"))  // 上次状态报警
						{
							device.setProberAlarmCount(device.getProberAlarmCount() - 1);// 探测器故障解除，恢复为0.
							for (Prober p : device.getProbers())
							{
								if (p.getLocation().equals(conMessage.getDeviceAddress()))
								{
									p.setCurrentStatus(recoveryStatusValue(errorDevices.get(address)[1]));
									p.setCurrentThickness(conMessage.getSimulation());
									p.setAlarmTime(timeColor(status, timeLaber));
									proberService.update(p);
								}
							}
						}
						else if(errorDevices.get(address)[2].equals("2"))   // 上次状态故障
						{
							device.setProberFaultCount(device.getProberFaultCount() - 1);// 探测器故障解除，恢复为0.
							for (Prober p : device.getProbers())
							{
								if (p.getLocation().equals(conMessage.getDeviceAddress()))
								{
									p.setCurrentStatus(recoveryStatusValue(errorDevices.get(address)[1]));
									p.setCurrentThickness(conMessage.getSimulation());
									p.setAlarmTime(timeColor(status, timeLaber));
									proberService.update(p);
								}
							}
							
						}
						deviceService.update(device);
						errorDevices.remove(address);
					}
					this.sendRecoverShortMessage(conMessage, device);
				}
				else   // 状态一直正常，但结果也要显示在监控列表中
				{
					for (Prober p : device.getProbers())
					{
						if (p.getLocation().equals(conMessage.getDeviceAddress()))
						{
							p.setCurrentThickness(conMessage.getSimulation());
							p.setAlarmTime("<td style=\"color:green\">" + timeLaber + "</td>");
							proberService.update(p);
						}
					}
				}
				handleProberForEarlyWarning(conMessage, device, timeLaber);    // 处理预警事件。
			}  // 状态正常结束
			// 状态不正常开始，则再分为故障或报警处理
			else if (status.equals("连线故障") || status.equals("探测器故障"))  // 故障处理
			{
				this.handleLineOrProberError(address, device, conMessage, pointInfo, timeLaber, status);
			}
			else  // 带浓度的不正常状态则报警，判断是否需要发短信
			{
				if (!errorDevices.containsKey(address)) // 如果不包含，就发短信
				{
					for (Prober p : device.getProbers())
					{
						if (p.getLocation().equals(conMessage.getDeviceAddress()))
						{
							p.setCurrentStatus(this.statusValueAndColor(status));
							p.setCurrentThickness(conMessage.getSimulation());
							p.setAlarmTime(timeColor(status, timeLaber));
							proberService.update(p);
						}
					}
					device.setProberAlarmCount(device.getProberAlarmCount() + 1);
					sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
					deviceService.update(device);
				}
				else // 包含该设备地址，但状态不同也发短信而不计数
				{
					if (errorDevices.get(address)[0].equals("2"))  // 上次是故障，本次是浓度报警
					{
						device.setProberAlarmCount(device.getProberAlarmCount() + 1); // 探测器发生故障，报警取消-1.
						device.setProberFaultCount(device.getProberFaultCount() - 1); // 探测器故障发生次数+1
						for (Prober p : device.getProbers())
						{
							if (p.getLocation().equals(conMessage.getDeviceAddress()))
							{
								p.setCurrentStatus(statusValueAndColor(status));
								p.setCurrentThickness(conMessage.getSimulation());
								p.setAlarmTime(timeColor(status, timeLaber));
								proberService.update(p);
							}
						}
						deviceService.update(device);
						this.sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
					}
					else if(errorDevices.get(address)[0].equals("7"))  // 上次设备是7，本次也是7，分两种情况
					{   // 上次浓度报警:1，本次浓度报警
						if (errorDevices.get(address)[2].equals("1") && !errorDevices.get(address)[1].equals(status)) // 判断报警及浓度
						{
							for (Prober p : device.getProbers()) {
								if (p.getLocation().equals(conMessage.getDeviceAddress()))
								{
									p.setCurrentStatus(statusValueAndColor(status));
									p.setCurrentThickness(conMessage.getSimulation());
									p.setAlarmTime(timeColor(status, timeLaber));
									proberService.update(p);
								}
							}
							this.sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
						}
						else if(errorDevices.get(address)[2].equals("2"))  // 上次是故障:2，本次是浓度报警
						{
							device.setProberAlarmCount(device.getProberAlarmCount() + 1);  // 探测器发生故障，报警取消-1.
							device.setProberFaultCount(device.getProberFaultCount() - 1); // 探测器故障发生次数+1
							for (Prober p : device.getProbers()) {
								if (p.getLocation().equals(conMessage.getDeviceAddress())) 
								{
									p.setCurrentStatus(statusValueAndColor(status));
									p.setCurrentThickness(conMessage.getSimulation());
									p.setAlarmTime(timeColor(status, timeLaber));
									proberService.update(p);
								}
							}
							deviceService.update(device);
							sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
						}
					}
				}
			}
		}
	}
	
	// 一个客户端关闭时
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("one Client Disconnect");
	}

	// 一个客户端接入时
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("one Client Connection");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("接收信息出错");
	}

	public String getParameter(String unit) {
		AlarmParameter parameter = alarmParameterService.findUnitVal(unit);
		return parameter.getStandConcentration();
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public AlarmParameterService getAlarmParameterService() {
		return alarmParameterService;
	}

	public void setAlarmParameterService(AlarmParameterService alarmParameterService) {
		this.alarmParameterService = alarmParameterService;
	}

	/**
	 * 系统恢复后的显示字符及颜色
	 * @param status
	 * @return
	 */
	private String recoveryStatusValue(String status)
	{
		if(status.equals("屏蔽"))
			return "<td style=\"color:#009900\">屏蔽恢复</td>";
		else if(status.equals("探测器故障"))
			return "<td style=\"color:#009900\">探测器恢复</td>";
		else if(status.equals("低开关量"))
			return "<td style=\"color:#009900\">低开关量恢复</td>";
		else if(status.equals("高开关量"))
			return "<td style=\"color:#009900\">高开关量恢复</td>";
		else if(status.equals("低限报警"))
			return "<td style=\"color:#009900\">低限恢复</td>";
		else if(status.equals("高限报警"))
			return "<td style=\"color:#009900\">高限恢复</td>";
		else if(status.equals("火警"))
			return "<td style=\"color:#009900\">火警解除</td>";
		else if(status.equals("故障"))
			return "<td style=\"color:#009900\">故障恢复</td>";
		else
			return "<td style=\"color:#009900\">恢复</td>";
	}
	
	private String statusValueAndColor(String status)
	{
		if(status.contains("报警"))
			return "<td style=\"color:red\">" + status + "</td>";       // 报警
		else
			return "<td style=\"color:#FFCC00\">" + status + "</td>";   // 故障
	}
	
	private String timeColor(String status, String time)
	{
		if(status.equals("")) // 状态正常
			return "<td style=\"color:green\">" + time + "</td>";
	    else if(status.contains("报警"))
			return "<td style=\"color:red\">" + time + "</td>";
		else
			return "<td style=\"color:#FFCC00\">" + time + "</td>";
	}
	
	/**
	 * 控制器复位
	 * @param deviceCode
	 */
	public void resetDevice(String deviceCode, Device device)
	{
		for (String key: errorDevices.keySet())
		{
			if (key.contains(deviceCode)) {
				errorDevices.remove(key);
			}
		}
		
		device.setAttachDeviceFaultCount(0);
		device.setProberAlarmCount(0);
		device.setProberFaultCount(0);
		device.setStatus("<td style=\"color:#009900\">正常</td>");
		
		for(Prober prober: device.getProbers())
		{
			prober.setCurrentStatus("<td style=\"color:#009900\">正常</td>");
			prober.setCurrentThickness(null);
			prober.setAlarmTime("<td style=\"color:#009900\"></td>");
		}
		
		for(AttachDevice attachDevice: device.getAttachDevices())
		{
			attachDevice.setAlarmTime("<td style=\"color:#009900\"></td>");
			attachDevice.setCurrentStatus("<td style=\"color:#009900\">正常</td>");
		}
		
		deviceService.update(device);
	}

	/**
	 * 处理控测器    连线故障/ 探测器故障
	 * @param address
	 * @param device
	 * @param conMessage
	 * @param pointInfo
	 * @param timeLaber
	 * @param status
	 */
	private void handleLineOrProberError(String address, Device device, ConcentrationMessage conMessage, PointInfo pointInfo, String timeLaber, String status)
	{
		if (!errorDevices.containsKey(address)) // 如果不包含，就发短信
		{
			for (Prober p : device.getProbers())
			{
				if (p.getLocation().equals(conMessage.getDeviceAddress()))
				{
					p.setCurrentStatus(this.statusValueAndColor(status));
					p.setCurrentThickness("0");
					p.setAlarmTime(timeColor(status, timeLaber));
				}
			}
			device.setProberFaultCount(device.getProberFaultCount() + 1);
			sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
			deviceService.update(device);
		}
		else // 包含该设备地址，但状态不同也发短信而不计数
		{
			if (errorDevices.get(address)[0].equals("2") && !errorDevices.get(address)[1].equals(status))  //上次是故障， 本次也是故障，同时故障不相同时
			{
				for (Prober p : device.getProbers())
				{
					if (p.getLocation().equals(conMessage.getDeviceAddress())) 
					{
						p.setCurrentStatus(statusValueAndColor(status));
						p.setCurrentThickness("0");
						p.setAlarmTime(timeColor(status, timeLaber));
						proberService.update(p);
					}
				}
				sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
			}
			else if(errorDevices.get(address)[0].equals("7"))  // 上次是7，本次也是7，分两种情况 
			{
				if (errorDevices.get(address)[2].equals("1")) {  // 上次是报警，这次故障
					device.setProberAlarmCount(device.getProberAlarmCount() - 1); // 探测器发生故障，报警取消-1.
					device.setProberFaultCount(device.getProberFaultCount() + 1); // 探测器故障发生次数+1
					for (Prober p : device.getProbers()) {
						if (p.getLocation().equals(
								conMessage.getDeviceAddress())) {
							p.setCurrentStatus(statusValueAndColor(status));
							p.setCurrentThickness("0");
							p.setAlarmTime(timeColor(status, timeLaber));
						}
					}
					deviceService.update(device);
					sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
				}
				else if(errorDevices.get(address)[2].equals("2"))  // 上次是故障，本次也故障
				{
					if (!errorDevices.get(address)[1].equals(status))
					{
						for (Prober p : device.getProbers()) {
							if (p.getLocation().equals(conMessage.getDeviceAddress())) {
								p.setCurrentStatus(statusValueAndColor(status));
								p.setCurrentThickness("0");
								p.setAlarmTime(timeColor(status, timeLaber));
								proberService.update(p);
							}
						}
						sendFAShortMessage(conMessage, device, pointInfo); // 发送过一次短信了，不再继续发短信（一次故障只发一次短信）
					}
				}
			}
		}
	}

	private void handleProberForEarlyWarning(ConcentrationMessage conMessage, Device device, String timeLaber)
	{
		float simulation = Float.parseFloat(conMessage.getSimulation().split("%")[0]);
		String address ="11" + device.getDeviceCode() + conMessage.getDeviceAddress(); // 此地址为该控制器下的探测器地址：控制器地址+探测器地址
		String[] flag = {"0"};  // 下标0为设备类型； 下标1为状态；下标2为设备类型为7时，“1”为报警“2”为故障.
		StringBuffer ew = new StringBuffer();
		StringBuffer recover = new StringBuffer();
		
		recover.append(device.getArea().getDepartment().getDepartmentName());
		recover.append(device.getArea().getAreaName());
		recover.append(device.getDeviceName() + "的");
		recover.append(conMessage.getDeviceAddress() + "探测器于"); // 探测器地址
		recover.append(conMessage.getTimeLaber() + "浓度预警解除。【东震】");

		String phoneNumbers = "";
		Set<User> us = device.getDeviceManagers();
		for (User user : us) {// 给多个发短信
			if (null != user.getPhoneNumber()) {
				phoneNumbers += user.getPhoneNumber() + ",";
			}
		}

		if(simulation >= 10)
		{
			if(!earlyWarningDevices.containsKey(address))  // 不包含该地址就发短信
			{
				ew.append(device.getArea().getDepartment().getDepartmentName());
				ew.append(device.getArea().getAreaName());
				ew.append(device.getDeviceName() + "的");
				ew.append(conMessage.getDeviceAddress() + "探测器于"); // 探测器地址
				ew.append(conMessage.getTimeLaber() + "发生浓度为");
				ew.append(conMessage.getSimulation() + "预警。【东震】");

				flag[0] = "1";
				earlyWarningDevices.put(address, flag);
				CallUtils.sendMsg(ew.toString(), phoneNumbers);
			}
		}
		else if(simulation < 10 && simulation > 0) // 浓度< 10, 同时> 0; 如果等于0，不作预警处理
		{
			if(earlyWarningDevices.containsKey(address))
			{
				CallUtils.sendMsg(recover.toString(), phoneNumbers);
				earlyWarningDevices.remove(address);
			}
		}
	}
	
}