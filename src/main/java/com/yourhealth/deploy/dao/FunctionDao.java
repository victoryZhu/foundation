package com.yourhealth.deploy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.deploy.domain.Function;

public interface FunctionDao extends JpaRepository<Function, Integer>,
		JpaSpecificationExecutor<Function> {
	
	/**
	 * 根据moduleId得到功能信息，按照code排序
	 * @param moduleId
	 * @return
	 */
	List<Function> findAllByModule_IdOrderByCodeAsc(Integer moduleId);		
	
	/**
	 * 取全部功能信息，按照模块组编号、模块编号、功能编号排序
	 * @return
	 */
	List<Function> findAllByOrderByModule_Category_CodeAscModule_CodeAscCodeAsc(); 

}
