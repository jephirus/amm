package com.dabizi.amm.receiver;

public class ControlValue {
	private int controlFlg ;
	private String controlAddr ;
	private int deviceFlg ;
	private String deviceAddr ;
	private String value ;
	private int status ;
	
	public ControlValue(int controlFlg, String controlAddr, int deviceFlg, String deviceAddr, String value, int status)
	{
		this.controlFlg = controlFlg ;
		this.controlAddr = controlAddr ;
		this.deviceFlg = deviceFlg ;
		this.deviceAddr = deviceAddr ;
		this.value = value ;
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
	public String getControAddr()
	{
		return controlAddr ;
	}
	
	public void setDeviceFlg(int deviceFlg)
	{
		this.deviceFlg = deviceFlg ;
	}
	public int getDeveiceFlg()
	{
		return deviceFlg ;
	}
	
	public void setDEviceAddr(String deviceAddr)
	{
		this.deviceAddr = deviceAddr ;
	}
	public String getDeviceAddr()
	{
		return deviceAddr ;
	}
	
	public void setValue(String value)
	{
		this.value = value ;
	}
	public String getValue()
	{
		return value ;
	}
	
	public void setStatus(int status)
	{
		this.status = status ;
	}
	public int getStatus()
	{
		return status ;
	}
	
}
