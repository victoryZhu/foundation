package com.yourhealth.orgnization.domain;

//import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_bmxx")
@JsonIgnoreProperties({"ryxxs"})
/**
 * 部门信息
 * @author zzm
 */
public class Bmxx implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	/** 部门编号，部门编号不能重复，varchar(10) */
	@Column(length=10)
	//@Size(min = 4, max = 10")
	private String bmbh;
	
	/** 部门名称，部门名称不能重复，varchar(36) */
	@Column(length=36)
	//@Size(min = 3, max = 12")
	private String bmmc;		
	
	/** 使用标志，0：注销/1：正常， SMALLINT */	
	@Column
	private boolean used;	
	
	/** 部门人员 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "bmxx")	
	private Set<Ryxx> ryxxs = new HashSet<Ryxx>();	
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBmbh() {
		return bmbh;
	}
	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}		
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}			
	public Set<Ryxx> getRyxxs() {
		return ryxxs;
	}
	public void setRyxxs(Set<Ryxx> ryxxs) {
		this.ryxxs = ryxxs;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Bmxx))  
            return false;  
          
        Bmxx o = (Bmxx)obj;  
        return new EqualsBuilder().append(getId(), o.getId()).isEquals();
	}
	
	@Override
	public int hashCode() {		
		//根据id创建hashCode，id对应一条具体的记录
		//使用getter和setter而不是直接引用成员变量。因为在ORM中有的时候成员变量会被延时加载，这些变量只有当getter方法被调用的时候才真正可用
		return new HashCodeBuilder().append(getId()).toHashCode();				
	}

}