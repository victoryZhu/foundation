package com.yourhealth.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.security.dao.RoleRyxxDao;
import com.yourhealth.security.domain.RoleRyxx;
import com.yourhealth.security.service.RoleRyxxService;

/**
 * 角色成员实现类
 * @author zzm
 *
 */
@Service("roleRyxxService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleRyxxServiceImpl implements RoleRyxxService {
	
	@Autowired
	private RoleRyxxDao roleRyxxDao;

	@Override
	public RoleRyxx save(RoleRyxx roleRyxx) {		
		return roleRyxxDao.save(roleRyxx);
	}
	
	@Override
	public List<RoleRyxx> save(List<RoleRyxx> roleRyxxs) {
		return roleRyxxDao.save(roleRyxxs);		
	}	

	@Override
	public void delete(RoleRyxx roleRyxx) {		
		roleRyxxDao.delete(roleRyxx);
	}

	@Override
	public void delete(Integer roleRyxxid) {
		roleRyxxDao.delete(roleRyxxid);
	}

	@Override
	public RoleRyxx findOne(Integer roleRyxxid) {		
		return roleRyxxDao.findOne(roleRyxxid);
	}

	@Override
	public void deleteByRyxxId(Integer ryxxid) {
		roleRyxxDao.deleteByRyxxId(ryxxid);		
	}

	@Override
	public Page<RoleRyxx> findAllByRoleId(Integer roleId, Pageable pageable) {		
		return roleRyxxDao.findAllByRoleId(roleId, pageable);
	}

	@Override
	public Page<RoleRyxx> findAllByRyxxId(Integer ryxxId, Pageable pageable) {
		return roleRyxxDao.findAllByRyxxId(ryxxId, pageable);
	}

}