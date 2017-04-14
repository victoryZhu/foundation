package com.yourhealth.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yourhealth.security.domain.SysLog;

public interface SysLogService {
	
	void save(SysLog sysLog);
		
	void delete(SysLog sysLog);
	
	void delete(Integer sysLogid);
			
	SysLog findOne(Integer sysLogid);
	
	/**
	 * 根据条件得到系统日志分页数据
	 * @param pageable
	 * @param condition
	 * @return
	 */
	Page<SysLog> querySysLogLists(Specification<SysLog> spec, Pageable pageable);	

}
