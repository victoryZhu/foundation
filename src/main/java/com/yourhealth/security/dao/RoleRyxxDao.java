package com.yourhealth.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.security.domain.RoleRyxx;

public interface RoleRyxxDao extends JpaRepository<RoleRyxx, Integer>,
		JpaSpecificationExecutor<RoleRyxx> {
	
	void deleteByRyxxId(Integer ryxxId);
	
	Page<RoleRyxx> findAllByRoleId(Integer roleId, Pageable pageable);
	
	Page<RoleRyxx> findAllByRyxxId(Integer ryxxId, Pageable pageable);

}
