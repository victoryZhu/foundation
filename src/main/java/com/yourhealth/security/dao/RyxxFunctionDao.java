package com.yourhealth.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.security.domain.RyxxFunction;

public interface RyxxFunctionDao extends JpaRepository<RyxxFunction, Integer>,
		JpaSpecificationExecutor<RyxxFunction> {
	
	void deleteByRyxx(Ryxx ryxx);
	
	List<RyxxFunction> findAllByRyxxIdOrderByFunctionCodeAsc(Integer ryxxId);

}
