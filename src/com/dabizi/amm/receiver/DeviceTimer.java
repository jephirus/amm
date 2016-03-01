package com.dabizi.amm.receiver;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.web.socket.TextMessage;

import com.dabizi.amm.uegateSoapInterfaceAxis.CallUtils;
import com.dabizi.amm.websocket.SystemWebSocketHandler;
import com.dabizi.point.model.PointInfo;
import com.dabizi.point.service.PointInfoService;

import cn.jxust.base.model.User;
import cn.jxust.device.model.Device;
import cn.jxust.device.service.DeviceService;
import cn.jxust.utils.DateUtils;

/**
 * @author Jephirus
 *
 */
public class DeviceTimer extends TimerTask
{
	@Resource
	private DeviceService deviceService;

	@Resource
	private SystemWebSocketHandler systemWebSocketHandler;
	
	@Resource
	private PointInfoService pointInfoService;
	
	private Device device;
	
	
	public DeviceTimer(Device device)
	{
		this.device = device;
	}
	
    @Override
    public void run()
    {
        if(device.getNetFaultFlag() == 0)
        {
        	device.setNetFaultFlag(1);
        	deviceService.update(device);
        }
        else
        {
        	sendDeviceMessage(device);
        }
  
    }
  
    /**
     * 网络故障定时
     * @param time:定时，为毫秒
     */
    public static void netFaultTimer(long time, Device device)
    {
        Timer timer = new Timer();
        timer.schedule(new DeviceTimer(device), 1000, time);
    }
    
	private void sendDeviceMessage(Device device) {
		
		StringBuffer msgSb = new StringBuffer();
		PointInfo pointInfo = device.getPointInfo();
		String sessionName = "";
		
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
		
		if (null != pointInfo)
		{
			pointInfo.setStatus(PointInfo.STATUS_ERROR);
			pointInfo.setStatusColor("yellow");
			sessionName = pointInfo.getLatitude() + "|" + pointInfo.getLongitude();
			pointInfoService.update(pointInfo);
		}
		
		phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
		CallUtils.sendMsg(msgSb.toString(), phoneNumbers);
		systemWebSocketHandler.sendMessageToIndex(new TextMessage(msgSb.toString() +"*#*" + sessionName +"*#*" + "2" + "*#*" + device.getArea().getAreaId()));
	}
    
}  
