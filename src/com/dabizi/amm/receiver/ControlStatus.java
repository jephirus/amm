package com.dabizi.amm.receiver;

public class ControlStatus {

	private int flg;
	private String addr ;
	private int status ;
	public ControlStatus(int flg, String addr, int status)
	{
		this.flg = flg ;
		this.addr = addr ;
		this.status = status ;
	}
	
	public void setFlg(int flg)
	{
		this.flg = flg ;
	}
	public int getFlg()
	{
		return flg ;
	}
	
	
	public void setAddr(String addr)
	{
		this.addr =addr ;
	}
	public String getAddr()
	{
		return addr ;
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
