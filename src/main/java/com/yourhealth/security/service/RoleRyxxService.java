package com.yourhealth.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yourhealth.security.domain.RoleRyxx;

/**
 * 角色成员接口类
 * @author zzm
 *
 */
public interface RoleRyxxService {
	
	RoleRyxx save(RoleRyxx roleRyxx);
	
	List<RoleRyxx> save(List<RoleRyxx> roleRyxxs);
	
	void delete(RoleRyxx roleRyxx);
	
	void delete(Integer roleRyxxid);
			
	RoleRyxx findOne(Integer roleRyxxid);	

	/**
	 * 分页查询
	 * @param roleId
	 * @param pageable
	 * @return
	 */	
	Page<RoleRyxx> findAllByRoleId(Integer roleId, Pageable pageable);
	
	/**
	 * 分页查询
	 * @param ryxxId
	 * @param pageable
	 * @return
	 */
	Page<RoleRyxx> findAllByRyxxId(Integer ryxxId, Pageable pageable);

	/**
	 * 根据ryxxid删除员工所有角色授权，一般用于员工离职时删除权限。
	 * @param ryxxid
	 * @return
	 */
	void deleteByRyxxId(Integer ryxxid);
	
}
