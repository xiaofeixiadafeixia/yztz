package cxw.yztz.entity;

public class College {
	private int college_id;
	private String name;
	private String region;
	
	public College() {
		// TODO Auto-generated constructor stub
	}
	public College(int college_id) {
		// TODO Auto-generated constructor stub
		this.college_id = college_id;
	}
	
	public College(int college_id, String name, String region) {
		super();
		this.college_id = college_id;
		this.name = name;
		this.region = region;
	}


	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
