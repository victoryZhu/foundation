package com.yourhealth.orgnization.domain;

//import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

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
import com.yourhealth.security.domain.RoleRyxx;
import com.yourhealth.security.domain.RyxxFunction;

@Entity
@Table(name = "t_ryxx")
@JsonIgnoreProperties({"roleRyxxs","ryxxFunctions"}) 
/**
 * 员工信息
 * @author zzm
 */
public class Ryxx implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	/** 员工编号，不能重复，varchar(10) */
	@Column(length=10)
	//@Size(min = 3, max = 10")，不允许有中文符号
	private String rybh;	
	
	/** 姓名，varchar(30) */
	@Column(length=30)
	//@Size(max = 10")
	private String name;	
	
	/** 性别，0：女/1：男，，varchar(1) */
	@Column(length=1)
	//@Size(max = 1")
	private String sex;	
			
	/** 职称，varchar(60) */
	@Column(length=60)
	//@Size(max = 20")
	private String zc;	
	
	/** 电子邮件，varchar(80) */
	@Column(length=80)
	//@Size(max = 80")
	private String email;	
	
	/** 雇佣标志，0：离职/1：在职，SMALLINT */
	@Column
	private boolean employed;			
	
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
		
	/** 所属部门*/
	@ManyToOne(optional=false)
	@JoinColumn(name="bmxxid")	
	private Bmxx bmxx = new Bmxx();	
			
	/** 人员的角色 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ryxx")	
	private Set<RoleRyxx> roleRyxxs = new HashSet<RoleRyxx>();
	
	/** 人员权限模块功能 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ryxx")	
	private Set<RyxxFunction> ryxxFunctions  = new HashSet<RyxxFunction>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRybh() {
		return rybh;
	}
	public void setRybh(String rybh) {
		this.rybh = rybh;
	}		
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
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
	public Bmxx getBmxx() {
		return bmxx;
	}
	public void setBmxx(Bmxx bmxx) {
		this.bmxx = bmxx;
	}
	public Set<RyxxFunction> getRyxxFunctions() {
		return ryxxFunctions;
	}
	public void setRyqxFunctions(Set<RyxxFunction> ryxxFunctions) {
		this.ryxxFunctions = ryxxFunctions;
	}
	public Set<RoleRyxx> getRoleRyxxs() {
		return roleRyxxs;
	}
	public void setRoleRyxxs(Set<RoleRyxx> roleRyxxs) {
		this.roleRyxxs = roleRyxxs;
	}
	public boolean isEmployed() {
		return employed;
	}
	public void setEmployed(boolean employed) {
		this.employed = employed;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
		
        if(!(obj instanceof Ryxx))  
            return false;  
          
        Ryxx o = (Ryxx)obj;  
        return new EqualsBuilder().append(getId(), o.getId()).isEquals();
	}

	@Override
	public int hashCode() {		
		//根据id创建hashCode，id对应一条具体的记录
		//使用getter和setter而不是直接引用成员变量。因为在ORM中有的时候成员变量会被延时加载，这些变量只有当getter方法被调用的时候才真正可用
		return new HashCodeBuilder().append(getId()).toHashCode();				
	}	

}