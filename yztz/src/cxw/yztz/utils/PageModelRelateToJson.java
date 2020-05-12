package cxw.yztz.utils;

import java.util.List;

/**
 * 使用json传输的分页模板
 * @author 24780
 *
 * @param <T>  数据类型
 */
public class PageModelRelateToJson <T>{
	private Integer total;//数据库里数据总量
	private List<T> record ;//返回的数据的集合
	private Integer pageNum;//一页有多少条数据   
	private Integer startIndex;//开始的索引
	private Integer currentNum;//当前所在页数
	private T t;
	
	public PageModelRelateToJson(Integer total,Integer pageNum,Integer currentNum) {
		this.total = total;
		this.currentNum = currentNum;
		this.pageNum = pageNum;
		this.startIndex = (this.currentNum-1)*this.pageNum;
	}
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRecord() {
		return record;
	}

	public void setRecord(List<T> record) {
		this.record = record;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}
	
	
}
