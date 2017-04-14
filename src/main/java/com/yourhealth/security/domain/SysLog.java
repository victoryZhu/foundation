package com.yourhealth.security.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yourhealth.orgnization.domain.Ryxx;

@Entity
@Table(name = "t_syslog")
public class SysLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;	
	
	/** 人员 */
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="ryxxid", nullable=false)
	private Ryxx ryxx;
	
	/** 操作：登录、登出， varchar(10) */
	@Column(length=10)
	private String opeType;
	
	/** 操作时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Calendar opeTime;
	
	/** IP地址， varchar(30)  */
	@Column(length=30)
	private String ip;
			
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ryxx getRyxx() {
		return ryxx;
	}
	public void setRyxx(Ryxx ryxx) {
		this.ryxx = ryxx;
	}
	public String getOpeType() {
		return opeType;
	}
	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}
	public Calendar getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Calendar opeTime) {
		this.opeTime = opeTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
		
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof SysLog))  
            return false;  
          
        SysLog o = (SysLog)obj;  
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