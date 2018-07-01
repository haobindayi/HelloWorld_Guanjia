package com.it.domain;

public class ZhangWu {
	  private int uid ;
	  private String  uname;
	  private double umoney;
	  private String ubank;
	  private String utime;
	  private String udesc;
	public ZhangWu() {}
	public ZhangWu(int uid, String uname, double umoney, String ubank, String utime, String udesc) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.umoney = umoney;
		this.ubank = ubank;
		this.utime = utime;
		this.udesc = udesc;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public double getUmoney() {
		return umoney;
	}
	public void setUmoney(double umoney) {
		this.umoney = umoney;
	}
	public String getUbank() {
		return ubank;
	}
	public void setUbank(String ubank) {
		this.ubank = ubank;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getUdesc() {
		return udesc;
	}
	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}
	@Override
	public String toString() {
		return "ZhangWu [uid=" + uid + ", uname=" + uname + ", umoney=" + umoney + ", ubank=" + ubank + ", utime="
				+ utime + ", udesc=" + udesc + "]";
	}
	
}
