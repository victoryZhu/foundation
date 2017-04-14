package com.yourhealth.deploy.domain;

import java.io.Serializable;

public class Org implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String id;
	private String pId;
	private String name;
	private String type;	
	private boolean isParent;
	private String icon;
	private String iconClose;
	private String iconOpen;
	private String nodeOpenUrl;
	private boolean drag;
	private boolean drop;
	private String ryxxid;

	public Org(String id,String pid,String name,String type,boolean isparent,String icon,String iconclose,String iconopen,String nodeOpenUrl){
		this.id=id;
		this.pId=pid;
		this.name=name;
		this.type=type;
		this.isParent=isparent;
		this.icon=icon;
		this.iconClose=iconclose;
		this.iconOpen=iconopen;
		this.nodeOpenUrl=nodeOpenUrl; 
	}
	
	public Org(String id,String pid,String name,String type,boolean isparent,String icon,String iconclose,String iconopen,String nodeOpenUrl,String jgxxid,boolean dragflag,boolean dropflag){
		this.id=id;
		this.pId=pid;
		this.name=name;
		this.type=type;
		this.isParent=isparent;
		this.icon=icon;
		this.iconClose=iconclose;
		this.iconOpen=iconopen;
		this.nodeOpenUrl=nodeOpenUrl;	
		this.setDrag(dragflag);
		this.setDrop(dropflag);
	}
	
	public Org(String id,String pid,String name,String type,boolean isparent,String icon,String iconclose,String iconopen,String nodeOpenUrl,String jgxxid,boolean dragflag,boolean dropflag,String ryxxid,boolean fullTime){
		this.id=id;
		this.pId=pid;
		this.name=name;
		this.type=type;
		this.isParent=isparent;
		this.icon=icon;
		this.iconClose=iconclose;
		this.iconOpen=iconopen;
		this.nodeOpenUrl=nodeOpenUrl;
		this.setDrag(dragflag);
		this.setDrop(dropflag);
		this.setRyxxid(ryxxid);
	}

	public String getNodeOpenUrl() {
		return nodeOpenUrl;
	}
	public void setNodeOpenUrl(String nodeOpenUrl) {
		this.nodeOpenUrl = nodeOpenUrl;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isDrag() {
		return drag;
	}
	public void setDrag(boolean dragflag) {
		this.drag = dragflag;
	}
	public boolean isDrop() {
		return drop;
	}
	public void setDrop(boolean dropflag) {
		this.drop = dropflag;
	}
	public String getRyxxid() {
		return ryxxid;
	}
	public void setRyxxid(String ryxxid) {
		this.ryxxid = ryxxid;
	}	
}
