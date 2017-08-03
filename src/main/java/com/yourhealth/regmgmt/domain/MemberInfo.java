package com.yourhealth.regmgmt.domain;

//import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.io.Serializable;
import java.util.Calendar;
//import java.util.HashSet;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.yourhealth.security.domain.RoleRyxx;
//import com.yourhealth.security.domain.RyxxFunction;

@Entity
@Table(name = "member_info")/*会员个人基本信息表*/
//@JsonIgnoreProperties({"roleRyxxs","ryxxFunctions"}) 
/**
 * 会员个人基本信息
 * @author zzm
 */
public class MemberInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 会员编号，不能重复，varchar(20) */
	@Id	
	@Column(length=20)
	//@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private String memberid;
	
	/** 会员类型，varchar(1) */
	@Column(length=1)
	//@Size(min = 3, max = 10")，不允许有中文符号
	private String type;	
	
	/** 管理等级，varchar(1) */
	@Column(length=1)
	//@Size(max = 10")
	private String grade;	
	
	/** 姓名，varchar(30) */
	@Column(length=30)
	//@Size(max = 1")
	private String name;	
	
	/** 生日，date */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")// HH:mm:ss",timezone="GMT+8")
	private Calendar birthdate;
		
	/** 性别，0：女/1：男，，varchar(1) */		
	@Column(length=1)
	//@Size(max = 20")
	private String sex;	
	
	/** 名族，varchar(20) */
	@Column(length=20)
	//@Size(max = 80")
	private String race;	
	
	/** 身份证，varchar（18） */
	@Column(length=18)
	private String idcard;			
	
	/** 职业，varchar(20) */
	@Column(length=20)
	//@Size(max = 20")，不允许有中文
	private String job ;
	
	/** 工作单位，varchar(80) */
	@Column(length=80)
	private String org;	
	
	/** 工作部门，varchar(40) */
	@Column(length=40)
	private String dept;	
		
	/** 文化程度，varchar(20) */
	@Column(length=20)
	private String education;
	
	/** 婚姻状况，varchar(1) */
	@Column(length=1)
	private String maritalstatus;
	
	/** 手机号，varchar(20) */
	@Column(length=20)
	private String cellphone;
	
	/** QQ，varchar(20) */
	@Column(length=20)
	private String qq;
	
	/** 微信号，varchar(20) */
	@Column(length=20)
	private String wechat;
	
	/** 座机号，varchar(20) */
	@Column(length=20)
	private String phone;
	
	/** 电子邮箱，varchar(50) */
	@Column(length=50)
	private String email;
	
	/** 家庭住址，varchar(80) */
	@Column(length=20)
	private String homeaddress;
	
	/** 建档日期，datetime */
	@Temporal(TemporalType.DATE)
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Calendar archiving;
	
	/** 高血压，varchar(30) */
	@Column(length=30)
	private String info_001;
	
	/** 糖尿病，varchar(30) */
	@Column(length=30)
	private String info_002;
	
	/** 冠心病，varchar(30) */
	@Column(length=30)
	private String info_003;
	
	/** 脑中风，varchar(30) */
	@Column(length=30)
	private String info_004;
	
	/** 肺癌，varchar(30) */
	@Column(length=30)
	private String info_005;
	
	/** 乳腺癌，varchar(30) */
	@Column(length=30)
	private String info_006;
	
	/** 结肠癌，varchar(30) */
	@Column(length=30)
	private String info_007;
	
	/** 宫颈癌，varchar(30) */
	@Column(length=30)
	private String info_008;
	
	/** 其他恶性肿瘤，varchar(30) */
	@Column(length=30)
	private String info_009;
	
	/** 高血压确诊年份，varchar(30) */
	@Column(length=30)
	private String info_010;
	
	/** 高血压药物史，varchar(30) */
	@Column(length=30)
	private String info_011;
	
	/** 高血压风险等级，varchar(30) */
	@Column(length=30)
	private String info_012;
	
	/** 糖尿病确诊年份，varchar(30) */
	@Column(length=30)
	private String info_013;
	
	/** 糖尿病药物史，varchar(30) */
	@Column(length=30)
	private String info_014;
	
	/** 糖尿病风险等级，varchar(30) */
	@Column(length=30)
	private String info_015;
	
	/** 冠心病确诊年份，varchar(30) */
	@Column(length=30)
	private String info_016;
	
	/** 冠心病药物史，varchar(30) */
	@Column(length=30)
	private String info_017;
	
	/** 冠心病风险等级，varchar(30) */
	@Column(length=30)
	private String info_018;
	
	/** 脑中风确诊年份，varchar(30) */
	@Column(length=30)
	private String info_019;
	
	/** 脑中风药物史，varchar(30) */
	@Column(length=30)
	private String info_020;
	
	/** 脑中风风险等级，varchar(30) */
	@Column(length=30)
	private String info_021;
	
	/** 超重，varchar(30) */
	@Column(length=30)
	private String info_022;
	
	/** 肥胖，varchar(30) */
	@Column(length=30)
	private String info_023;
	
	/** 血压异常，varchar(30) */
	@Column(length=30)
	private String info_024;
	
	/** 血糖异常，varchar(30) */
	@Column(length=30)
	private String info_025;
	
	/** 尿酸异常，varchar(30) */
	@Column(length=30)
	private String info_026;
	
	/** 吸烟，varchar(30) */
	@Column(length=30)
	private String info_027;
	
	/** 肉食过量，varchar(30) */
	@Column(length=30)
	private String info_028;
	
	/** 蔬菜不足，varchar(30) */
	@Column(length=30)
	private String info_029;
	
	/** 饮酒过量，varchar(30) */
	@Column(length=30)
	private String info_030;
	
	/** 体力活动不足，varchar(30) */
	@Column(length=30)
	private String info_031;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_032;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_033;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_034;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_035;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_036;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_037;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_038;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_039;
	
	/** 备用，varchar(30) */
	@Column(length=30)
	private String info_040;
	
	public String getMemberid() {
	    return memberid;
    }
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Calendar getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}	
	
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}	
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}	
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}	
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}	
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}	
	
	public String getMaritalstatus() {
		return maritalstatus;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}	
	
	public String getQQ() {
		return qq;
	}
	public void setQQ(String qq) {
		this.qq = qq;
	}
	
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
	
	public Calendar getArchiving() {
		return archiving;
	}
	public void setArchiving(Calendar archiving) {
		this.archiving = archiving;
	}
		
	public String getInfo_001() {
		return info_001;
	}
	public void setInfo_001(String info_001) {
		this.info_001 = info_001;
	}
	
	public String getInfo_002() {
		return info_002;
	}
	public void setInfo_002(String info_002) {
		this.info_002 = info_002;
	}
	
	public String getInfo_003() {
		return info_003;
	}
	public void setInfo_003(String info_003) {
		this.info_003 = info_003;
	}
	
	public String getInfo_004() {
		return info_004;
	}
	public void setInfo_004(String info_004) {
		this.info_004 = info_004;
	}
	
	public String getInfo_005() {
		return info_005;
	}
	public void setInfo_005(String info_005) {
		this.info_005 = info_005;
	}
	
	public String getInfo_006() {
		return info_006;
	}
	public void setInfo_006(String info_006) {
		this.info_006 = info_006;
	}
	
	public String getInfo_007() {
		return info_007;
	}
	public void setInfo_007(String info_007) {
		this.info_007 = info_007;
	}
	
	public String getInfo_008() {
		return info_008;
	}
	public void setInfo_008(String info_008) {
		this.info_008 = info_008;
	}
	
	public String getInfo_009() {
		return info_009;
	}
	public void setInfo_009(String info_009) {
		this.info_009 = info_009;
	}
	
	public String getInfo_010() {
		return info_010;
	}
	public void setInfo_010(String info_010) {
		this.info_010 = info_010;
	}
	
	public String getInfo_011() {
		return info_011;
	}
	public void setInfo_011(String info_011) {
		this.info_011 = info_011;
	}
	
	public String getInfo_012() {
		return info_012;
	}
	public void setInfo_012(String info_012) {
		this.info_012 = info_012;
	}
	
	public String getInfo_013() {
		return info_013;
	}
	public void setInfo_013(String info_013) {
		this.info_013 = info_013;
	}
	
	public String getInfo_014() {
		return info_014;
	}
	public void setInfo_014(String info_014) {
		this.info_014 = info_014;
	}
	
	public String getInfo_015() {
		return info_015;
	}
	public void setInfo_015(String info_015) {
		this.info_015 = info_015;
	}
	
	public String getInfo_016() {
		return info_016;
	}
	public void setInfo_016(String info_016) {
		this.info_016 = info_016;
	}
	
	public String getInfo_017() {
		return info_017;
	}
	public void setInfo_017(String info_017) {
		this.info_017 = info_017;
	}
	
	public String getInfo_018() {
		return info_018;
	}
	public void setInfo_018(String info_018) {
		this.info_018 = info_018;
	}
	
	public String getInfo_019() {
		return info_019;
	}
	public void setInfo_019(String info_019) {
		this.info_019 = info_019;
	}
	
	public String getInfo_020() {
		return info_020;
	}
	public void setInfo_020(String info_020) {
		this.info_020 = info_020;
	}
	
	public String getInfo_021() {
		return info_021;
	}
	public void setInfo_021(String info_021) {
		this.info_021 = info_021;
	}
	
	public String getInfo_022() {
		return info_022;
	}
	public void setInfo_022(String info_022) {
		this.info_022 = info_022;
	}
	
	public String getInfo_023() {
		return info_023;
	}
	public void setInfo_023(String info_023) {
		this.info_023 = info_023;
	}
	
	public String getInfo_024() {
		return info_024;
	}
	public void setInfo_024(String info_024) {
		this.info_024 = info_024;
	}
	
	public String getInfo_025() {
		return info_025;
	}
	public void setInfo_025(String info_025) {
		this.info_025 = info_025;
	}
	
	public String getInfo_026() {
		return info_026;
	}
	public void setInfo_026(String info_026) {
		this.info_026 = info_026;
	}
	
	public String getInfo_027() {
		return info_027;
	}
	public void setInfo_027(String info_027) {
		this.info_027 = info_027;
	}
	
	public String getInfo_028() {
		return info_028;
	}
	public void setInfo_028(String info_028) {
		this.info_028 = info_028;
	}
	
	public String getInfo_029() {
		return info_029;
	}
	public void setInfo_029(String info_029) {
		this.info_029 = info_029;
	}
	
	public String getInfo_030() {
		return info_030;
	}
	public void setInfo_030(String info_030) {
		this.info_030 = info_030;
	}
	
	public String getInfo_031() {
		return info_031;
	}
	public void setInfo_031(String info_031) {
		this.info_031 = info_031;
	}
	
	public String getInfo_032() {
		return info_032;
	}
	public void setInfo_032(String info_032) {
		this.info_032 = info_032;
	}
	
	public String getInfo_033() {
		return info_033;
	}
	public void setInfo_033(String info_033) {
		this.info_033 = info_033;
	}
	
	public String getInfo_034() {
		return info_034;
	}
	public void setInfo_034(String info_034) {
		this.info_034 = info_034;
	}
	
	public String getInfo_035() {
		return info_035;
	}
	public void setInfo_035(String info_035) {
		this.info_035 = info_035;
	}
	
	public String getInfo_036() {
		return info_036;
	}
	public void setInfo_036(String info_036) {
		this.info_036 = info_036;
	}
	
	public String getInfo_037() {
		return info_037;
	}
	public void setInfo_037(String info_037) {
		this.info_037 = info_037;
	}
	
	public String getInfo_038() {
		return info_038;
	}
	public void setInfo_038(String info_038) {
		this.info_038 = info_038;
	}
	
	public String getInfo_039() {
		return info_039;
	}
	public void setInfo_039(String info_039) {
		this.info_039 = info_039;
	}
	
	public String getInfo_040() {
		return info_040;
	}
	public void setInfo_040(String info_040) {
		this.info_040 = info_040;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if(obj == this)  
            return true;  
		
		if (obj == null)
			return false;
		
        if(!(obj instanceof MemberInfo))  
            return false;  
          
        MemberInfo o = (MemberInfo)obj;  
        return new EqualsBuilder().append(getMemberid(), o.getMemberid()).isEquals();
	}

	@Override
	public int hashCode() {		
		//根据id创建hashCode，id对应一条具体的记录
		//使用getter和setter而不是直接引用成员变量。因为在ORM中有的时候成员变量会被延时加载，这些变量只有当getter方法被调用的时候才真正可用
		return new HashCodeBuilder().append(getMemberid()).toHashCode();				
	}	

}