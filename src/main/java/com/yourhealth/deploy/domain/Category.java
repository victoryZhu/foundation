package com.yourhealth.deploy.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_category")
@JsonIgnoreProperties({"modules"})
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
	
	@Column(length=5)
	/** varchar(5)， 模块组编号，不可重复，一般是5个字符，如：YH001、YH002 */
	private String code;	
	
	/** varchar(60)， 模块组名称 */
	@Column(length=60)
	//@Size(min = 3, max = 20")
	private String name;	
	
	/** 模块组的模块 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "category")	
	private Set<Module> modules = new HashSet<Module>();	
				
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}		
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
		
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Category))  
            return false;  
          
        Category o = (Category)obj;  
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