package cxw.yztz.entity;

import java.util.Date;
import java.util.Set;

public class Product implements Cloneable{
	private Integer goods_id;
	private User user;//所属用户
	private Type type_product;//所属类别
	private String name;//商品名称
	private String info;//商品详情
	private Double  fineness;//成色
	private Double price;//商品价格
	private Date time;//上架日期
	private Integer state;//上架状态     1：在售   2：下架   3售罄 4删除
	private College college;
	private Set<Picture> pictures;//图片
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Product)super.clone();
	}
	public Product(Integer goods_id, User user, Type type_product, String name, String info, Double fineness,
			Double price, Date time, Integer state, College college, Set<Picture> pictures) {
		super();
		this.goods_id = goods_id;
		this.user = user;
		this.type_product = type_product;
		this.name = name;
		this.info = info;
		this.fineness = fineness;
		this.price = price;
		this.time = time;
		this.state = state;
		this.college = college;
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "Product [goods_id=" + goods_id + ", user=" + user + ", type_product=" + type_product + ", name="
				+ name + ", info=" + info + ", fineness=" + fineness + ", price=" + price + ", time=" + time
				+ ", state=" + state + ", college=" + college + ", pictures=" + pictures + "]";
	}

	

	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType_product() {
		return type_product;
	}

	public void setType_product(Type type_product) {
		this.type_product = type_product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getFineness() {
		return fineness;
	}

	public void setFineness(Double fineness) {
		this.fineness = fineness;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}
	
	
	
	
	
}
