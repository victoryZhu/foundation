package com.yourhealth.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.common.domain.DictionaryItem;

/**
 * 数据字典项DAO接口
 * @author zzm
 *
 */
public interface DictionaryItemDao extends
		JpaRepository<DictionaryItem, Integer>, JpaSpecificationExecutor<DictionaryItem> {			
	
	/**
	 * 用于修改时检查名称是否和其他对象的名称重复
	 * @param name
	 * @param id
	 * @return
	 */
	Long countByNameAndIdNot(String name, Integer id);

}
