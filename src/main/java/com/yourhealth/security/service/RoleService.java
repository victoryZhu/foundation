package com.yourhealth.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yourhealth.security.domain.Authorization;
import com.yourhealth.security.domain.Role;

/**
 * 角色接口类
 * @author zzm
 *
 */
public interface RoleService {
		
	Role save(Role role);
	
	void delete(Role role);
	
	void delete(Integer roleid);
					
	Role findOne(Integer roleid);	
	

	/**
	 * 根据条件得到角色分页数据
	 * @param pageable
	 * @param condition
	 * @return
	 */
	Page<Role> findAll(Specification<Role> spec, Pageable pageable);	
	
	/**
	 * 验证角色编号，编号不能重复
	 * @param opeType 
	 * @param role
	 * @return
	 */
	boolean checkRoleCode(String opeType, Role role);

	/**
	 * 验证角色名称，名称不能重复
	 * @param opeType 
	 * @param role
	 * @return
	 */
	boolean checkRoleName(String opeType, Role role);

	/**
	 * 根据roleId得到角色功能，用于浏览
	 * @param roleId
	 * @return
	 */
	String getRoleFunctionTreeForShow(Integer roleId);

	/**
	 * 根据roleId得到角色功能，用于编辑
	 * @param roleId
	 * @return
	 */
	List<Authorization> getRoleFunctionTreeForEdit(Integer roleId);
		
}