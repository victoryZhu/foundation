package com.yourhealth.security.service;

import java.util.List;

import com.yourhealth.security.domain.Role;
import com.yourhealth.security.domain.RoleFunction;

/**
 * 角色功能接口类
 * @author zzm
 *
 */
public interface RoleFunctionService {
	
	void save(RoleFunction roleFunction);
	
	void delete(RoleFunction roleFunction);
	
	void delete(Integer roleFunctionid);
			
	RoleFunction findOne(Integer roleFunctionid);
	
	List<RoleFunction> findAllByRoleIdOrderByFunctionCodeAsc(Integer roleId);
	
	/**
	 * 删除角色的所有功能
	 * @param roleid
	 * @return 
	 */
	Integer delRoleFunctions(Integer roleid);

	/**
	 * 新增多个角色功能
	 * @param role
	 * @param funcids
	 */
	void save(Role role, Integer[] funcids);
		
}
