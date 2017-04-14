package com.yourhealth.common.domain;

import java.util.List;
import java.util.Map;

/**
 * 通用选取Grid
 * @author zzm
 *
 */
public class SelectGrid {
	
	/** 总页数 */
	private Long total;
	/** 当前页 */
	private int page;
	/** 总记录数 */
	private long records;
	/** 列表数据 */
	private List<Map<String,String>> rows;
	/** 附加信息，目前没有使用，用于以后扩展用 */
	private Map<String,Object> userdata;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long long1) {
		this.total = long1;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getRecords() {
		return records;
	}
	public void setRecords(long records) {
		this.records = records;
	}	
	public List<Map<String, String>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}
	public Map<String, Object> getUserdata() {
		return userdata;
	}
	public void setUserdata(Map<String, Object> userdata) {
		this.userdata = userdata;
	}
	
}
