package com.yourhealth.deploy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.deploy.domain.Module;

public interface ModuleDao extends JpaRepository<Module, Integer>,
		JpaSpecificationExecutor<Module> {
	
	/**
	 * 根据categoryId得到模块信息，按照code排序
	 * @param categoryId
	 * @return
	 */
	List<Module> findAllByCategory_IdOrderByCodeAsc(Integer categoryId);

}
