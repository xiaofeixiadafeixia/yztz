package cxw.yztz.entity;

public class Address {
	private int address_id;//id（主键）
	private	Integer user_id;//用户id
	private int college_id;//学院id
	private String address;//详细地址
	private String pseudonym;//收货姓名
	private String phone;//联系电话
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", user_id=" + user_id + ", college_id=" + college_id
				+ ", address=" + address + ", pseudonym=" + pseudonym + ", phone=" + phone + "]";
	}

	public Address(int address_id, int user_id, int college_id, String address, String pseudonym, String phone) {
		super();
		this.address_id = address_id;
		this.user_id = user_id;
		this.college_id = college_id;
		this.address = address;
		this.pseudonym = pseudonym;
		this.phone = phone;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}
