package com.yourhealth.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.security.dao.RyxxFunctionDao;
import com.yourhealth.security.domain.RyxxFunction;
import com.yourhealth.security.service.RyxxFunctionService;

/**
 * 人员权限功能实现类
 * @author zzm
 *
 */
@Service("ryxxFunctionService")
@Transactional(propagation = Propagation.REQUIRED)
public class RyxxFunctionServiceImpl implements RyxxFunctionService {
	
	@Autowired
	private RyxxFunctionDao ryxxFunctionDao;

	@Override
	public void save(RyxxFunction ryxxFunction) {
		ryxxFunctionDao.save(ryxxFunction);
	}

	@Override
	public void delete(RyxxFunction ryxxFunction) {
		ryxxFunctionDao.delete(ryxxFunction);
	}

	@Override
	public void delete(Integer ryxxFunctionid) {
		ryxxFunctionDao.delete(ryxxFunctionid);
	}

	@Override
	public RyxxFunction findOne(Integer ryxxFunctionid) {
		return ryxxFunctionDao.findOne(ryxxFunctionid);
	}
	
	@Override
	public List<RyxxFunction> findAllByRyxxIdOrderByFunctionCodeAsc(
			Integer ryxxId) {
		return ryxxFunctionDao.findAllByRyxxIdOrderByFunctionCodeAsc(ryxxId);
	}
	
	@Override
	public void delRyxxFunction(Ryxx ryxx) {
		ryxxFunctionDao.deleteByRyxx(ryxx);		
	}	

}
