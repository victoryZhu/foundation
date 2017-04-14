package com.yourhealth.deploy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.deploy.domain.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>,
		JpaSpecificationExecutor<Category> {	
			
	/**
	 * 取所有模块组信息，按照编号排序
	 * @return
	 */
	List<Category> findAllByOrderByCodeAsc();

}
