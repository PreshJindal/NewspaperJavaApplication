package customerviewer;

public class CustomerBean {
	String cname;
	String address;
	String area;
	String hawker;
	String mobile;
	String selpaper;
	String selprice;
	String dos;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getHawker() {
		return hawker;
	}
	public void setHawker(String hawker) {
		this.hawker = hawker;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSelpaper() {
		return selpaper;
	}
	public void setSelpaper(String selpaper) {
		this.selpaper = selpaper;
	}
	public String getSelprice() {
		return selprice;
	}
	public void setSelprice(String selprice) {
		this.selprice = selprice;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public CustomerBean(String cname, String address, String area, String hawker, String mobile, String selpaper,
			String selprice, String dos) {
		super();
		this.cname = cname;
		this.address = address;
		this.area = area;
		this.hawker = hawker;
		this.mobile = mobile;
		this.selpaper = selpaper;
		this.selprice = selprice;
		this.dos = dos;
	}
	
	
}
