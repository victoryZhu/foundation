package com.yourhealth.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.security.domain.Role;

public interface RoleDao extends JpaRepository<Role, Integer>,
		JpaSpecificationExecutor<Role> {

	Long countByCode(String code);
	
	Long countByCodeAndIdNot(String code, Integer id);
	
	Long countByName(String name);
	
	Long countByNameAndIdNot(String name, Integer id);

}
