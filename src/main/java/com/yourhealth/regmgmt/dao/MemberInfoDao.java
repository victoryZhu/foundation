package com.yourhealth.regmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.regmgmt.domain.MemberInfo;

public interface MemberInfoDao extends JpaRepository<MemberInfo, Integer>,
		JpaSpecificationExecutor<MemberInfo> {
	
	Long countByMemberid(String memberid);
	
	//Long countByBmbhAndIdNot(String bmbh, Integer id);
	
	//Long countByBmmc(String bmmc);
	
	//Long countByBmmcAndIdNot(String bmbh, Integer id);

}
