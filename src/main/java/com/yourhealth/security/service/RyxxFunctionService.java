package com.yourhealth.security.service;

import java.util.List;

import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.security.domain.RyxxFunction;

/**
 * 人员权限功能接口类
 * @author zzm
 *
 */
public interface RyxxFunctionService {
	
	void save(RyxxFunction ryxxFunction);
	
	void delete(RyxxFunction ryxxFunction);
	
	void delete(Integer ryxxFunctionid);
			
	RyxxFunction findOne(Integer ryxxFunctionid);	
	
	List<RyxxFunction> findAllByRyxxIdOrderByFunctionCodeAsc(Integer ryxxId);
	
	/**
	 * 删除人员所有权限，用于人员离职
	 * @param ryxx
	 */
	void delRyxxFunction(Ryxx ryxx);	

}
