package cxw.yztz.entity;

import java.util.Date;

public class Leave {
	private Integer leave_id;
	private User user;
	private Integer goods_id;
	private String content;
	private Date time;
	private Integer type;//1.留言 2.评论
	
	public Leave() {
		
	}
	public Leave(Integer leave_id, User user, Integer goods_id, String content, Date time, Integer type) {
		super();
		this.leave_id = leave_id;
		this.user = user;
		this.goods_id = goods_id;
		this.content = content;
		this.time = time;
		this.type = type;
	}

	public Integer getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(Integer leave_id) {
		this.leave_id = leave_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	
}
