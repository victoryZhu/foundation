package com.yourhealth.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yourhealth.common.domain.DictionaryItem;

/**
 * 数据字典项Service接口类
 * @author zzm
 *
 */
public interface DictionaryItemService {	
	
	/**
	 * 带条件的分页数据
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<DictionaryItem> findAll(Specification<DictionaryItem> spec, Pageable pageable);	
	
	/**
	 * 分页数据
	 * @param pageable
	 * @return
	 */
	Page<DictionaryItem> findAll(Pageable pageable);
	
	/**
	 * 保存数据字典项
	 * @param dictionaryItem
	 */
	DictionaryItem save(DictionaryItem dictionaryItem);

	/**
	 * 检查数据字典项的逻辑名是否重复
	 * @param dictionaryItem
	 * @return
	 */	
	boolean chkItemName(DictionaryItem dictionaryItem);	

}