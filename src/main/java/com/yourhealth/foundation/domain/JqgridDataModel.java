package com.yourhealth.foundation.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class JqgridDataModel<T> {
	
	private int total;   //totalPages
	private int page;   //currentPage
	private long records;  //totalRecords
	private List<T> rows;
	private List<Map<String, Object>> userdata;
	
	public JqgridDataModel(T obj) {
		this.page = 1;
		this.total = 1;
		this.records = 1;
		this.rows = new ArrayList<T>(1);
		this.rows.add(obj);
	}
	
	public JqgridDataModel(Page<T> pageData) {
		this.page = pageData.getNumber() + 1;
		this.total = pageData.getTotalPages();
		this.records = pageData.getTotalElements();
		this.rows = pageData.getContent();
	}
	
	public JqgridDataModel(List<T> listAllData) {
		this.page = 1;
		this.total = 1;
		this.records = listAllData.size();
		this.rows = listAllData;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<Map<String, Object>> getUserdata() {
		return userdata;
	}
	public void setUserdata(List<Map<String, Object>> userdata) {
		this.userdata = userdata;
	}

}
