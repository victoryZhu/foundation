package com.yourhealth.common.domain;

/**
 * 通用选取对象信息 
 */
public class SelectObject {
    
    //对象属性
	private String id; //序号，从0开始连续编号
	private String objectPyName; //对象名称
	private String objectAsName; //对象別名
	private String objectLgName; //对象逻辑名，用于通用选取页面抬头显示
	private String objectWidth; //对象宽度，单位：像素
	private String objectHeight; //对象高度，单位：像素
	private String orderName; //排序字段
	private String orderType; //排序方式

	//字段属性
	private String[] fieldPyName; //字段名称
	private String[] fieldAsName; //字段別名
	private String[] fieldLgName; //字段逻辑名
	private String[] fieldType; //字段类型：char、select、date等	
	private String[] fieldValue; //字段显示：例如设定下拉框对照值 
	private String[] fieldWidth; //字段的列宽，使用比例或像素待定
	private boolean[] isSearch; //字段是否能夠作为搜索条件
	private boolean[] isShow; //字段是否显示
			
	public String[] getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String[] fieldValue) {
		this.fieldValue = fieldValue;
	}		
	public String[] getFieldAsName() {
		return fieldAsName;
	}

	public String[] getFieldLgName() {
		return fieldLgName;
	}

	public String[] getFieldPyName() {
		return fieldPyName;
	}

	public String[] getFieldType() {
		return fieldType;
	}
	
	public boolean[] getIsSearch() {
		return isSearch;
	}

	public String getOrderName() {
		return orderName;
	}

	public boolean[] getIsShow() {
		return isShow;
	}

	public String getOrderType() {
		return orderType;
	}
		
	public void setFieldAsName(String[] fieldAsName) {
		this.fieldAsName = fieldAsName;
	}

	public void setFieldLgName(String[] fieldLgName) {
		this.fieldLgName = fieldLgName;
	}

	public void setFieldPyName(String[] fieldPyName) {
		this.fieldPyName = fieldPyName;
	}

	public void setFieldType(String[] fieldType) {
		this.fieldType = fieldType;
	}
	
	public void setIsSearch(boolean[] isSearch) {
		this.isSearch = isSearch;
	}

	public void setIsShow(boolean[] isShow) {
		this.isShow = isShow;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}	
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(String[] fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	public String getObjectPyName() {
		return objectPyName;
	}

	public void setObjectPyName(String objectPyName) {
		this.objectPyName = objectPyName;
	}

	public String getObjectAsName() {
		return objectAsName;
	}

	public void setObjectAsName(String objectAsName) {
		this.objectAsName = objectAsName;
	}

	public String getObjectLgName() {
		return objectLgName;
	}

	public void setObjectLgName(String objectLgName) {
		this.objectLgName = objectLgName;
	}

	public String getObjectWidth() {
		return objectWidth;
	}

	public void setObjectWidth(String objectWidth) {
		this.objectWidth = objectWidth;
	}

	public String getObjectHeight() {
		return objectHeight;
	}

	public void setObjectHeight(String objectHeight) {
		this.objectHeight = objectHeight;
	}	
	
}