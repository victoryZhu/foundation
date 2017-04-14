package com.yourhealth.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.security.domain.RoleFunction;

public interface RoleFunctionDao extends JpaRepository<RoleFunction, Integer>,
		JpaSpecificationExecutor<RoleFunction> {

	List<RoleFunction> findAllByRoleIdOrderByFunctionCodeAsc(Integer roleId);

	/**
	 * 删除角色的所有模块功能
	 * 
	 * @param roleid
	 * @return
	 */
	int deleteByRoleId(Integer roleId);

}
