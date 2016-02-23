package com.dabizi.amm.receiver;

public class DeviceStatus {
	private int controlFlg ;
	private String controlAddr ;
	private int deviceFlg ;
	private String deviceAddr ;
	private int status ;
	
	public DeviceStatus(int controlFlg, String controlAddr, int deviceFlg, String deviceAddr, int status)
	{
		this.controlFlg = controlFlg ;
		this.controlAddr = controlAddr ;
		this.deviceFlg =deviceFlg ;
		this.deviceAddr = deviceAddr ;
		this.status = status ;
	}
	
	public void setControlFlg(int controlFlg)
	{
		this.controlFlg = controlFlg ;
	}
	public int getControlFlg()
	{
		return controlFlg ;
	}
	
	public void setControlAddr(String controlAddr)
	{
		this.controlAddr = controlAddr ;
	}
	public String getControlAddr()
	{
		return controlAddr ;
	}
	
	public void setDeviceFlg(int deviceFLg)
	{
		this.deviceFlg = deviceFLg ;
	}
	public int getDeviceFlg()
	{
		return deviceFlg ;
	}
	
	public void setDeviceAddr(String deviceAddr)
	{
		this.deviceAddr = deviceAddr ;
	}
	public String getDeviceAddr()
	{
		return deviceAddr ;
	}
	
	public void setStatus(int status)
	{
		this.status = status ;
	}
	public int setStatus()
	{
		return status ;
	}
}
