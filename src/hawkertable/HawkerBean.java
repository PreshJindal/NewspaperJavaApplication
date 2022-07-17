package hawkertable;

public class HawkerBean {
	String name;
	String address;
	String contact;
	String selareas;
	String acardno;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSelareas() {
		return selareas;
	}
	public void setSelareas(String selareas) {
		this.selareas = selareas;
	}
	public String getAcardno() {
		return acardno;
	}
	public void setAcardno(String acardno) {
		this.acardno = acardno;
	}
	public HawkerBean(String name, String address, String contact, String selareas, 
			String acardno) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.selareas = selareas;
		this.acardno = acardno;
	}	
	
		
}
