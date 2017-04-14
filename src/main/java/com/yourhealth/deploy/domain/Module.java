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

@Entity
@Table(name = "t_module")
@JsonIgnoreProperties({"functions"})
public class Module implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
	
	@Column(length=7)
	/** varchar(7)，模块编号，不可重复，一般是7个字符，如：SE00101、SE00203 */
	private String code;
	
	/** varchar(60)，模块名称 */
	@Column(length=60)
	//@Size(min = 4, max = 20")
	private String name;
	
	/** varchar(150)，模块url，系统菜单 */
	@Column(length=150)
	//@Size(min = 8, max = 150")，不允许有中文
	private String moduleUrl;	
		
	/** 模块的功能 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "module")	
	private Set<Function> functions = new HashSet<Function>();
	
	/** 所属模块组 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="categoryid")
	private Category category;
		
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}	
	public Set<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}	
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof Module))  
            return false;  
          
        Module o = (Module)obj;  
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