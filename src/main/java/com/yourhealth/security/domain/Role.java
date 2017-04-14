package com.yourhealth.security.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 系统角色对象
 * @author zzm
 *
 */
@Entity
@Table(name = "t_role")
@JsonIgnoreProperties({ "roleFunctions", "roleRyxxs"})
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	/** 角色编号，varchar(20) */
	@Column(length=20)
	//@Size(min = 3, max = 20")，不允许有中文
	private String code;
	
	/** 角色名称，varchar(36) */
	@Column(length=36)
	//@Size(min = 3, max = 12")
	private String name;	
		
	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Calendar createTime;
		
	/** 角色权限模块功能 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "role")	
	private Set<RoleFunction> roleFunctions  = new HashSet<RoleFunction>();
	
	/** 角色人员 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "role")	
	private Set<RoleRyxx> roleRyxxs  = new HashSet<RoleRyxx>();
	
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
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	public Set<RoleFunction> getRoleFunctions() {
		return roleFunctions;
	}
	public void setRoleFunctions(Set<RoleFunction> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	public Set<RoleRyxx> getRoleRyxxs() {
		return roleRyxxs;
	}
	public void setRoleRyxxs(Set<RoleRyxx> roleRyxxs) {
		this.roleRyxxs = roleRyxxs;
	}		
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Role))  
            return false;  
          
        Role o = (Role)obj;  
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