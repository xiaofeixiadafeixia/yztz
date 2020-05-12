package cxw.yztz.entity;

import java.util.Date;

public class BrowseHistory {
	private BrowseHistoryUionPKID browseHistoryUionPKID;
	private Date time ;
	
	
	public BrowseHistory() {
		
	}
	public BrowseHistory(BrowseHistoryUionPKID browseHistoryUionPKID, Date time) {
		super();
		this.browseHistoryUionPKID = browseHistoryUionPKID;
		this.time = time;
	}
	@Override
	public String toString() {
		return "BrowseHistory [browseHistoryUionPKID=" + browseHistoryUionPKID + ", time=" + time + "]";
	}
	
	public BrowseHistoryUionPKID getBrowseHistoryUionPKID() {
		return browseHistoryUionPKID;
	}
	public void setBrowseHistoryUionPKID(BrowseHistoryUionPKID browseHistoryUionPKID) {
		this.browseHistoryUionPKID = browseHistoryUionPKID;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
