package com.yourhealth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.security.dao.SysLogDao;
import com.yourhealth.security.domain.SysLog;
import com.yourhealth.security.service.SysLogService;

@Service("sysLogService")
@Transactional(propagation = Propagation.REQUIRED)
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public void save(SysLog sysLog) {
		sysLogDao.save(sysLog);
	}

	@Override
	public void delete(SysLog sysLog) {
		sysLogDao.delete(sysLog);
	}

	@Override
	public void delete(Integer sysLogid) {
		sysLogDao.delete(sysLogid);
	}

	@Override
	public SysLog findOne(Integer sysLogid) {		
		return sysLogDao.findOne(sysLogid);
	}

	@Override
	public Page<SysLog> querySysLogLists(Specification<SysLog> spec, Pageable pageable) {		
		return sysLogDao.findAll(spec, pageable);
	}

}
