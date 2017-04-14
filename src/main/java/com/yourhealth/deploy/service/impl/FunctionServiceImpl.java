package com.yourhealth.deploy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.deploy.dao.FunctionDao;
import com.yourhealth.deploy.domain.Function;
import com.yourhealth.deploy.service.FunctionService;

/**
 * 功能service实现类
 * @author zzm
 *
 */
@Service("functionService")
@Transactional(propagation = Propagation.REQUIRED)
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	private FunctionDao functionDao;

	@Override
	public Function save(Function function) {
		return functionDao.save(function);
	}

	@Override
	public void delete(Function function) {
		functionDao.delete(function);
	}

	@Override
	public void delete(Integer id) {
		functionDao.delete(id);
	}

	@Override
	public Function findOne(Integer id) {		
		return functionDao.findOne(id);
	}

}