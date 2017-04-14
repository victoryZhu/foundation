package com.yourhealth.orgnization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.orgnization.dao.BmxxDao;
import com.yourhealth.orgnization.domain.Bmxx;
import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.orgnization.service.BmxxService;

/**
 * 部门服务实现类
 * @author zzm
 *
 */
@Service("bmxxService")
@Transactional(propagation = Propagation.REQUIRED)
public class BmxxServiceImpl implements BmxxService {
	
	//private static final Logger logger = LoggerFactory.getLogger(BmxxServiceImpl.class);	
	
	@Autowired
	private BmxxDao bmxxDao;
	
	@Override
	public Bmxx save(Bmxx bmxx) {				
		return bmxxDao.save(bmxx);
	}

	@Override
	public Bmxx findOne(Integer bmxxid) {		
		return bmxxDao.findOne(bmxxid);
	}
	
	@Override
	public Page<Bmxx> findAll(Specification<Bmxx> spec, Pageable pageable) {		
		return bmxxDao.findAll(spec, pageable);		
	}
	
	@Override
	public Page<Bmxx> findAll(Pageable pageable) {
		return bmxxDao.findAll(pageable);
	}
	
	@Override
	public boolean checkBmbh(String opeType, Bmxx bmxx) {
		Long num = 0L;
		if("ADD".equals(opeType)){
			num = bmxxDao.countByBmbh(bmxx.getBmbh());
		}else{
			num = bmxxDao.countByBmbhAndIdNot(bmxx.getBmbh(), bmxx.getId());;
		}
		
		return num > 0?true:false;
	}	

	@Override
	public boolean checkBmmc(String opeType, Bmxx bmxx) {
		Long num = 0L;
		if("ADD".equals(opeType)){
			num = bmxxDao.countByBmmc(bmxx.getBmmc());
		}else{
			num = bmxxDao.countByBmmcAndIdNot(bmxx.getBmmc(), bmxx.getId());;
		}
		
		return num > 0?true:false;
	}
	
	@Override
	public boolean canStopBmxx(Integer bmxxId) {
		Bmxx bmxx = findOne(bmxxId);
		if(bmxx.isUsed()){
			for(Ryxx ryxx : bmxx.getRyxxs()){				
				if(ryxx.isEmployed()) {					
					return false;
				}
			}	
		}				
		return true;
	}	

}