package com.yourhealth.deploy.service;

import com.yourhealth.deploy.domain.Category;

/**
 * 模块组service实现类
 * @author zzm
 *
 */
public interface CategoryService {
	
	Category save(Category category);
	
	void delete(Category category);
	
	void delete(Integer id);
	
	Category findOne(Integer id);
	
	/** 
	 * 检查模块组节点能否删除
	 * @param id
	 * @return
	 */
	boolean canDelCategory(Integer id);
	
	/**
	 * 检查模块组节点能否删除 
	 * @param category
	 * @return
	 */
	boolean canDelCategory(Category category);
	
	/**
	 * 得到所有模块组、模块和功能的Json字符串，用于树形结构展示。
	 * @return
	 */
	String queryAllJsonCategorys();
	
}
