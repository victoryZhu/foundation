package com.yourhealth.security.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.yourhealth.deploy.domain.Function;
import com.yourhealth.orgnization.domain.Ryxx;

/**
 * 人员权限功能
 * @author zzm
 *
 */
@Entity
@Table(name = "t_ryxxfunction")
public class RyxxFunction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
		
	/** 主表外键 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="functionid")	
	private Function function;
	
	/** 人员权限 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="ryxxid")
	private Ryxx ryxx;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}		
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}		
	public Ryxx getRyxx() {
		return ryxx;
	}
	public void setRyxx(Ryxx ryxx) {
		this.ryxx = ryxx;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof RyxxFunction))  
            return false;  
          
        RyxxFunction o = (RyxxFunction)obj;  
        return new EqualsBuilder().append(getId(), o.getId()).isEquals();
	}

	@Override
	public int hashCode() {		
		//根据id创建hashCode，id对应一条具体的记录
		//使用getter和setter而不是直接引用成员变量。因为在ORM中有的时候成员变量会被延时加载，这些变量只有当getter方法被调用的时候才真正可用
		return new HashCodeBuilder().append(getId()).toHashCode();				
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}
		
}