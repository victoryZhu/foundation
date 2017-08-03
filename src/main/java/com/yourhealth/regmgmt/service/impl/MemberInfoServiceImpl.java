package com.yourhealth.regmgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.regmgmt.dao.MemberInfoDao;
import com.yourhealth.regmgmt.domain.MemberInfo;
import com.yourhealth.regmgmt.service.MemberInfoService;


/**
 * 会员个人基本信息服务实现类
 * @author yh
 *
 */
@Service("memberinfoService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberInfoServiceImpl implements MemberInfoService {
	
	//private static final Logger logger = LoggerFactory.getLogger(BmxxServiceImpl.class);	
	
	@Autowired
	private MemberInfoDao memberinfodao;
	
	@Override
	public MemberInfo save(MemberInfo memberinfo) {				
		return memberinfodao.save(memberinfo);
	}

	@Override
	public MemberInfo findOne(Integer memberid) {		
		return memberinfodao.findOne(memberid);
	}
	
	@Override
	public Page<MemberInfo> findAll(Specification<MemberInfo> spec, Pageable pageable) {		
		return memberinfodao.findAll(spec, pageable);		
	}
	
	@Override
	public Page<MemberInfo> findAll(Pageable pageable) {
		return memberinfodao.findAll(pageable);
	}
	
	@Override
	public boolean checkMemberid(String opeType, MemberInfo memberinfo) {
		Long num = 0L;
		if("ADD".equals(opeType)){
			num = memberinfodao.countByMemberid(memberinfo.getMemberid());
		}//else{
			//num = memberinfodao..countByBmbhAndIdNot(memberinfo.getMemberid(), memberinfo.getId());;
		//}
		
		return num > 0?true:false;
	}	

}