package cxw.yztz.entity;

import java.io.Serializable;

public class CollectUionPKID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6850170941733948078L;
	
	private Integer user_id;
	private Product product;
	
	public CollectUionPKID() {
		
	}
	public CollectUionPKID(Integer user_id, Product product) {
		super();
		this.user_id = user_id;
		this.product = product;
	}


	@Override
	public String toString() {
		return "CollectUionPKID [user_id=" + user_id + ", product=" + product.getGoods_id() + "]";
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectUionPKID other = (CollectUionPKID) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	
	
	
}
