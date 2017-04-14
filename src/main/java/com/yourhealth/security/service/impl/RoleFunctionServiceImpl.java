package com.yourhealth.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




//import com.chc.foundation.util.TimeComm;








import com.yourhealth.deploy.domain.Function;
import com.yourhealth.security.dao.RoleFunctionDao;
import com.yourhealth.security.domain.Role;
import com.yourhealth.security.domain.RoleFunction;
import com.yourhealth.security.service.RoleFunctionService;

/**
 * 角色功能实现类
 * @author zzm
 *
 */
@Service("roleFunctionService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleFunctionServiceImpl implements RoleFunctionService {
	
	@Autowired
	private RoleFunctionDao roleFunctionDao;

	@Override
	public void save(RoleFunction roleFunction) {
		roleFunctionDao.save(roleFunction);
	}

	@Override
	public void delete(RoleFunction roleFunction) {
		roleFunctionDao.delete(roleFunction);
	}

	@Override
	public void delete(Integer roleFunctionid) {
		roleFunctionDao.delete(roleFunctionid);
	}

	@Override
	public RoleFunction findOne(Integer roleFunctionid) {		
		return roleFunctionDao.findOne(roleFunctionid);
	}

	@Override
	public List<RoleFunction> findAllByRoleIdOrderByFunctionCodeAsc(
			Integer roleId) {		
		return roleFunctionDao.findAllByRoleIdOrderByFunctionCodeAsc(roleId);
	}	
	
	@Override
	public Integer delRoleFunctions(Integer roleId) {
		return roleFunctionDao.deleteByRoleId(roleId);		
	}

	@Override
	public void save(Role role, Integer[] idx) {	
		List<RoleFunction> lists = new ArrayList<RoleFunction>();
		for(int i=0;i<idx.length;i++){
			RoleFunction roleFunction = new RoleFunction();					
			Function function = new Function();
			function.setId(idx[i]);			
			roleFunction.setFunction(function);						
			roleFunction.setRole(role);		
			lists.add(roleFunction);
		}
		roleFunctionDao.save(lists);		
	}	
	
}