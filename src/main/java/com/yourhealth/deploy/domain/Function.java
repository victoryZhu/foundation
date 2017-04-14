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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yourhealth.security.domain.RoleFunction;
import com.yourhealth.security.domain.RyxxFunction;

@Entity
@Table(name = "t_function")
@JsonIgnoreProperties({"roleFunctions", "ryxxFunctions"})   //
public class Function implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
	
	@Column(length=9)
	/** varchar(50)，功能编号，不可重复，一般是9个字符，如：SE0010101、SE0020312 */
	private String code;	
	
	/** varchar(60)，功能名称 */
	@Column(length=60)
	//@Size(min = 3, max = 20")
	private String name;		
	
	/** 系统模块 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="moduleid")
	private Module module;
	
	/** 角色模块功能 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "function")	
	private Set<RoleFunction> roleFunctions = new HashSet<RoleFunction>();
	
	/** 人员模块功能 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "function")	
	private Set<RyxxFunction> ryxxFunctions = new HashSet<RyxxFunction>();
				
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
	public Set<RoleFunction> getRoleFunctions() {
		return roleFunctions;
	}
	public void setRoleFunctions(Set<RoleFunction> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	public Set<RyxxFunction> getRyxxFunctions() {
		return ryxxFunctions;
	}
	public void setRyxxFunctions(Set<RyxxFunction> ryxxFunctions) {
		this.ryxxFunctions = ryxxFunctions;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}	
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Function))  
            return false;  
          
        Function o = (Function)obj;  
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