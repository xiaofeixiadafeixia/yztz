package cxw.yztz.entity;

public class Type {
	private Integer type_id;
	private String name;
	
	public Type() {
		// TODO Auto-generated constructor stub
	}

	public Type(Integer type_id, String name) {
		super();
		this.type_id = type_id;
		this.name = name;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Type [type_id=" + type_id + ", name=" + name + "]";
	}

}
