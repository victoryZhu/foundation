package com.yourhealth.foundation.domain;

public class SystemMenuDataModel {
	private String id;
	private String text;
	private String url;
	private Type type;
	private String parentId;
	
	public SystemMenuDataModel(String id, String text, String url, Type type, String parentId){
		this.id = id;
		this.text = text;
		this.url = url;
		this.type = type;
		this.parentId = parentId;
	}
	
	public SystemMenuDataModel() {
		
	}

	public enum Type {
		CATEGORY, MODULE
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
}
