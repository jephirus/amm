package com.dabizi.amm.receiver;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import cn.jxust.base.model.User;
import cn.jxust.device.model.Device;
import cn.jxust.device.service.DeviceService;
import cn.jxust.utils.DateUtils;

import com.dabizi.amm.uegateSoapInterfaceAxis.CallUtils;
import com.dabizi.amm.websocket.SystemWebSocketHandler;
import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

/**
 * @author Jephirus 控制器定时，用于发现网故障。
 */
@Component
public class DeviceTimer {
	@Resource
	private DeviceService deviceService;

	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;

	@Resource
	private PointInfoService pointInfoService;

	public void doIt() {
		for (Device device : deviceService.getAll()) {
			if (device.getNetFaultFlag() == 0) {
				device.setNetFaultFlag(1);
				deviceService.update(device);
			} else {
				device.setStatus("<td id=\"" + device.getDeviceCode() + "status\" style=\"color:#FFFF00\">网络故障</td>"); // 设置界面状态颜色
				device.setDeviceFalutFlag(1); // 设置标记位
				deviceService.update(device);
				sendDeviceMessage(device);
			}
		}

	}

	private void sendDeviceMessage(Device device) {

		StringBuffer msgSb = new StringBuffer();
		PointInfo pointInfo = device.getPointInfo();
		String mapNode = "";

		msgSb.append(device.getArea().getDepartment().getDepartmentName());
		msgSb.append(device.getArea().getAreaName());
		msgSb.append(device.getDeviceName() + "于");
		msgSb.append(DateUtils.getCurrentTime() + "发生网络故障。【东震】");

		String phoneNumbers = "";
		Set<User> us = device.getDeviceManagers();
		for (User user : us) {// 给多个发短信
			if (null != user.getPhoneNumber()) {
				phoneNumbers += user.getPhoneNumber() + ",";
			}
		}

		if (null != pointInfo) {
			pointInfo.setStatus(PointInfo.STATUS_ERROR);
			pointInfo.setStatusColor("yellow");
			mapNode = pointInfo.getLatitude() + "|"
					+ pointInfo.getLongitude();
			pointInfoService.update(pointInfo);
		}

		phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
		CallUtils.sendMsg(msgSb.toString(), phoneNumbers);
		
		systemWebSocketHandler.sendMessageToIndex(new TextMessage(msgSb.toString() + "*#*"
				+ mapNode + "*#*" 			// 地图结点	
				+ "1" + "*#*"				// 序号：2，其值为1，表示控制器故障
				+ "网络故障" + "*#*"	// 当前控制器状态
				+ device.getDeviceCode()));					// 控制器编码  序号：4
	}

}
