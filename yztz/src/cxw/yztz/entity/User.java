package cxw.yztz.entity;


import java.util.Date;

public class User implements Cloneable{
	private int id;
	private String name;
	private String sex;
	private Date record_date;//注册日期
	private String email;
	private String info;
	private String pic;//头像
	private String password;
	private Date last_date;//上次登录时间
	private int message_index;//上次提示索引
	private int remind_type;//提醒标识
	
	
	public User() {
		
	}
	@Override
   public Object clone() throws CloneNotSupportedException {
        return  (User)super.clone();
    }

		
	public User(int id, String name, String sex, Date record_date, String email,  String info, String pic,
			 String password, Date last_date, int message_index, int remind_type) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.record_date = record_date;
		this.email = email;
		this.info = info;
		this.pic = pic;
		this.password = password;
		this.last_date = last_date;
		this.message_index = message_index;
		this.remind_type = remind_type;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", record_date=" + record_date + ", email="
				+ email + ", info=" + info + ", pic=" + pic + ", password=" + password + ", last_date=" + last_date
				+ ", message_index=" + message_index + ", remind_type=" + remind_type + "]";
	}


	public Date getLast_date() {
		return last_date;
	}


	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}


	public int getMessage_index() {
		return message_index;
	}


	public void setMessage_index(int message_index) {
		this.message_index = message_index;
	}


	public int getRemind_type() {
		return remind_type;
	}


	public void setRemind_type(int remind_type) {
		this.remind_type = remind_type;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getRecord_date() {
		return record_date;
	}
	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
