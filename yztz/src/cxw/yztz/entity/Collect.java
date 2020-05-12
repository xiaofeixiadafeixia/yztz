package cxw.yztz.entity;

import java.util.Date;

public class Collect {
	private CollectUionPKID uionPKID;
	private Date time;
	
	public Collect() {
		
	}

	public Collect(CollectUionPKID uionPKID, Date time) {
		super();
		this.uionPKID = uionPKID;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Collect [uionPKID=" + uionPKID + ", time=" + time + "]";
	}

	public CollectUionPKID getUionPKID() {
		return uionPKID;
	}

	public void setUionPKID(CollectUionPKID uionPKID) {
		this.uionPKID = uionPKID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	
	
}
