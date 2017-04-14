package com.yourhealth.security.domain;

public class Authorization {
	
	private String id;
	private String idx;
	private String functionname;
	private String level;
	private String parent;
	private String isLeaf;
	private String enbl;
	/*String expanded;
	String loaded;*/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getFunctionname() {
		return functionname;
	}
	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getEnbl() {
		return enbl;
	}
	public void setEnbl(String enbl) {
		this.enbl = enbl;
	}	
}
