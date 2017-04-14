package com.yourhealth.common.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;



import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "t_dictionaryValue") 
public class DictionaryValue implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	//Spring MVC接收json数据转DictionaryValue，新增时id为''，字符串不能转为int会抛出错误，改为Integer
	private Integer id;	
	
	/** varchar(24)，编号 */
	@Column(length=24)
	//@Size(min = 1, max = 24, message = "{dictionaryValue.code.size}")，不允许有中文字符
	private String code;
	
	/** varchar(60)，值 */
	@Column(length=60)
	//@Size(min = 1, max = 20, message = "{dictionaryValue.value.size}")
	private String value;
	
	/** varchar(1)，类型（S/U）：系统定义的值不可以删改，用户定义的可以增删改 */
	@Column(length=1)
	private String type;	
	
	/** varchar(120)，备注 */
	//@Size(min = 0, max = 40, message = "{dictionaryValue.memo.size}")
	@Column(length=120)
	private String memo;
	
	/** 主表外键 */
	@ManyToOne(cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name="dictionaryItemId")	
	private DictionaryItem dictionaryItem;
		
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public DictionaryItem getDictionaryItem() {
		return dictionaryItem;
	}
	public void setDictionaryItem(DictionaryItem dictionaryItem) {
		this.dictionaryItem = dictionaryItem;
	}	
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof DictionaryValue))  
            return false;  
          
        DictionaryValue o = (DictionaryValue)obj;  
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