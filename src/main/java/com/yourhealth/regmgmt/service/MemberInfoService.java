package com.yourhealth.regmgmt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yourhealth.regmgmt.domain.MemberInfo;;

/**
 * 会员个人基本信息服务接口类
 * @author zzm
 *
 */
public interface MemberInfoService {	
		
	/** 
	 * 保存会员个人基本信息
	 * @param MemberInfo
	 * @return
	 */
	MemberInfo save(MemberInfo memberinfo);
			
	/** 
	 * 根据memberid得到会员信息
	 * @param memberid
	 * @return
	 */
	MemberInfo findOne(Integer memberid);	
	
	/**
	 * 得到会员信息分页数据
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<MemberInfo> findAll(Specification<MemberInfo> spec, Pageable pageable);
	
	/**
	 * 得到会员信息分页数据
	 * @param pageable
	 * @return
	 */
	Page<MemberInfo> findAll(Pageable pageable);
			
	/** 
	 * 检查会员号是否已存在
	 * @param memberinfo
	 * @param opeType
	 * @return
	 */
	boolean checkMemberid(String opeType, MemberInfo memberinfo);

	/** 
	 * 检查公司中部门名称是否已存在
	 * @param bmxx
	 * @return
	 */
	//boolean checkName(String opeType, MemberInfo memberinfo);
		
	/** 
	 * 检查部门能否注销，部门的人员都离职部门才可以注销
	 * @param bmxxId
	 * @param opeType
	 * @return
	 */
	//boolean canStopBmxx(Integer bmxxId);

}