package cxw.yztz.entity;

import java.util.Date;

public class Order {
	private Integer orders_id;
	private Integer user_id;
	private Product product;
	private Address address;
	private Double money;
	private Date order_time;
	private Date finish_time;
	private Integer type;
	
	public Order() {
		
	}

	public Order(Integer orders_id, Integer user_id, Product product, Address address, Double money, Date order_time,
			Date finish_time, Integer type) {
		super();
		this.orders_id = orders_id;
		this.user_id = user_id;
		this.product = product;
		this.address = address;
		this.money = money;
		this.order_time = order_time;
		this.finish_time = finish_time;
		this.type = type;
	}

	public Integer getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(Integer orders_id) {
		this.orders_id = orders_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

	
	
}
