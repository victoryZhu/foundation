package com.yourhealth.deploy.service;

import com.yourhealth.deploy.domain.Function;

/**
 * 功能service接口类
 * @author zzm
 *
 */
public interface FunctionService {
	
	Function save(Function function);
	
	void delete(Function function);
	
	void delete(Integer id);
	
	Function findOne(Integer id);	

}
