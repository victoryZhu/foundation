package com.yourhealth.orgnization.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yourhealth.orgnization.domain.Bmxx;

/**
 * 部门服务接口类
 * @author zzm
 *
 */
public interface BmxxService {	
		
	/** 
	 * 保存部门
	 * @param bmxx
	 * @return
	 */
	Bmxx save(Bmxx bmxx);
			
	/** 
	 * 根据id得到部门
	 * @param bmxxid
	 * @return
	 */
	Bmxx findOne(Integer bmxxid);	
	
	/**
	 * 得到部门分页数据
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<Bmxx> findAll(Specification<Bmxx> spec, Pageable pageable);
	
	/**
	 * 得到部门分页数据
	 * @param pageable
	 * @return
	 */
	Page<Bmxx> findAll(Pageable pageable);
			
	/** 
	 * 检查公司中部门编号是否已存在
	 * @param bmxx
	 * @param opeType
	 * @return
	 */
	boolean checkBmbh(String opeType, Bmxx bmxx);

	/** 
	 * 检查公司中部门名称是否已存在
	 * @param bmxx
	 * @return
	 */
	boolean checkBmmc(String opeType, Bmxx bmxx);
		
	/** 
	 * 检查部门能否注销，部门的人员都离职部门才可以注销
	 * @param bmxxId
	 * @param opeType
	 * @return
	 */
	boolean canStopBmxx(Integer bmxxId);

}