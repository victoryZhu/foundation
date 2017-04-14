package com.yourhealth.orgnization.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "t_administrator")
/**
 * 员工信息
 * @author zzm
 */
public class Administrator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 唯一id，不可为空，唯一索引 */
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
		
	/** 姓名，varchar(30) */
	@Column(length=30)
	//@Size(max = 10")
	private String name;	
		
	/** 用户名（不可重复），varchar(20) */
	@Column(length=20)
	//@Size(max = 20")，不允许有中文
	private String username;
	
	/** 密码（加密保存），varchar(80) */
	@Column(length=80)
	private String password;	
	
	/** 账号是否启用，true:已受理;false:未受理，SMALLINT */
	@Column
	private boolean enabled;
	
	/** 角色名称 */
	@Column(length=20)
	private String roleName;	
	
	/** 电子邮件，varchar(80) */
	@Column(length=80)
	//@Size(max = 80")
	private String email;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}		
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Bmxx))  
            return false;  
          
        Administrator o = (Administrator)obj;  
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