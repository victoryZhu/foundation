package com.yourhealth.common.domain;

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
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_dictionaryItem")
@JsonIgnoreProperties({"dictionaryValues"})
public class DictionaryItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
	
	/** varchar(24)，代码 */
	@Column(length=24)
	private String code;	
	
	// 注意 :在这里检验长度的最大值和最小值时是不区分中英文，一个中文字和一个英文字母的长度是一样的，
	//      但是实际保持数据库时是不一样的，一个中文字符等于两个或三个英文字符。
	//@Size(min = 1, max = 45, message = "{dictionaryItem.name.size}") 
	//@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{username.nospaces}")
	/** varchar(45)，名称 */
	@Column(length=45)
	private String name;
	
	/** varchar(120)，备注 */
	//@Size(min = 0, max = 40, message = "{dictionaryItem.memo.size}")
	@Column(length=120)
	private String memo;	
	
	/** 数据字典值 */
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "dictionaryItem")	
	private Set<DictionaryValue> dictionaryValues = new HashSet<DictionaryValue>();
		
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}	
	public Set<DictionaryValue> getDictionaryValues() {
		return dictionaryValues;
	}
	public void setDictionaryValues(Set<DictionaryValue> dictionaryValues) {
		this.dictionaryValues = dictionaryValues;
	}	
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof DictionaryItem))  
            return false;  
          
        DictionaryItem o = (DictionaryItem)obj;  
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