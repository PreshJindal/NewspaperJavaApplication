package billtable;

public class BillBean {
	String mobile;
	String dos;
	String dupto;
	int Bill;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDupto() {
		return dupto;
	}
	public void setDupto(String dupto) {
		this.dupto = dupto;
	}
	public int getBill() {
		return Bill;
	}
	public void setBill(int bill) {
		Bill = bill;
	}
	public BillBean(String mobile, String dos, String dupto, int bill) {
		super();
		this.mobile = mobile;
		this.dos = dos;
		this.dupto = dupto;
		Bill = bill;
	}
	
	
}
