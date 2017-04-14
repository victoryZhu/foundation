package com.yourhealth.orgnization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.orgnization.dao.AdministratorDao;
import com.yourhealth.orgnization.domain.Administrator;
import com.yourhealth.orgnization.service.AdministratorService;

/**
 * 管理员信息服务实现类
 * @author zzm
 *
 */
@Service("administratorService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdministratorServiceImpl implements AdministratorService {
	
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Administrator save(Administrator administrator) {
		return administratorDao.save(administrator);
	}

	@Override
	public Administrator findOne(Integer id) {
		return administratorDao.findOne(id);
	}

	@Override
	public Administrator findOneByUsername(String username) {
		return administratorDao.findOneByUsername(username);
	}

	@Override
	public boolean checkPassword(Integer id, String oldyhkl) {		
		return passwordEncoder.matches(oldyhkl, findOne(id).getPassword());
	}

	@Override
	public void updPassword(Integer id, String password) {
		administratorDao.updPassword(id, passwordEncoder.encode(password));				
	}

}
