package cxw.yztz.entity;

public class Picture {
	private Integer picture_id;
	private Integer type;
	private Product goods;
	private String url;
	private String description;
	
	public Picture() {
		// TODO Auto-generated constructor stub
	}

	public Picture(Integer picture_id, Integer type, Product goods, String url, String description) {
		super();
		this.picture_id = picture_id;
		this.type = type;
		this.goods = goods;
		this.url = url;
		this.description = description;
	}

	public Integer getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(Integer picture_id) {
		this.picture_id = picture_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Product getGoods() {
		return goods;
	}

	public void setGoods(Product goods) {
		this.goods = goods;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Picture [picture_id=" + picture_id + ", type=" + type +  ", url=" + url
				+ ", description=" + description + "]";
	}
	
}
