package com.yourhealth.deploy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.deploy.dao.ModuleDao;
import com.yourhealth.deploy.domain.Module;
import com.yourhealth.deploy.service.ModuleService;

/**
 * 模块service实现类
 * @author zzm
 *
 */
@Service("moduleService")
@Transactional(propagation = Propagation.REQUIRED)
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleDao moduleDao;
	
	@Override
	public Module save(Module module) {
		return moduleDao.save(module);
	}

	@Override
	public void delete(Module module) {
		moduleDao.delete(module);
	}

	@Override
	public void delete(Integer id) {
		moduleDao.delete(id);
	}

	@Override
	public Module findOne(Integer id) {		
		return moduleDao.findOne(id);
	}

	@Override
	public boolean canDelModule(Integer id) {
		return canDelModule(findOne(id));
	}

	@Override
	public boolean canDelModule(Module module) {
		if(module.getFunctions()==null) 
			return true;
		
		if(module.getFunctions().size()>0) 
			return false;
		else
			return true;
	}

}
