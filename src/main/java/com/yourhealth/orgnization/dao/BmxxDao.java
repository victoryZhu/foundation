package com.yourhealth.orgnization.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.orgnization.domain.Bmxx;

public interface BmxxDao extends JpaRepository<Bmxx, Integer>,
		JpaSpecificationExecutor<Bmxx> {
	
	Long countByBmbh(String bmbh);
	
	Long countByBmbhAndIdNot(String bmbh, Integer id);
	
	Long countByBmmc(String bmmc);
	
	Long countByBmmcAndIdNot(String bmbh, Integer id);

}
