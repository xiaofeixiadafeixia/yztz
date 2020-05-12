package cxw.yztz.entity;

import java.io.Serializable;

public class BrowseHistoryUionPKID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3861933763745075875L;
	
	private Integer user_id;
	private Product product;
	public BrowseHistoryUionPKID() {
		
	}
	public BrowseHistoryUionPKID(Integer user_id, Product product) {
		super();
		this.user_id = user_id;
		this.product = product;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BrowseHistoryUionPKID [user_id=" + user_id + ", product=" + product.getGoods_id() + "]";
	}
	
	
}
