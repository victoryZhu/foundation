package com.yourhealth.common.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 通用选取DAO接口类
 * @author zzm
 *
 */
public interface SelectDao {
	
	/**
	 * 通用选取查询
	 * @param objName   查询对象
	 * @param listNames  查询对象的列名
	 * @param pageable
	 * @param condition  
	 * @return
	 */
	Page<Map<String, String>> selectDataLists(String objName, String[] listNames, Pageable pageable, String condition);

}
