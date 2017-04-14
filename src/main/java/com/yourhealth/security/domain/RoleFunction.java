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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yourhealth.deploy.domain.Function;

/**
 * 角色功能对象
 * @author zzm
 *
 */
@Entity
@Table(name = "t_rolefunction")
@JsonIgnoreProperties({"role"})
public class RoleFunction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	/** 模块功能 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="functionid")	
	private Function function;
	
	/** 角色 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="roleid")
	private Role role;
			
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}	
		
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof RoleFunction))  
            return false;  
          
        RoleFunction o = (RoleFunction)obj;  
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