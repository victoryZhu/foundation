package com.yourhealth.common.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.common.domain.DictionaryValue;

/**
 *  数据字典值接口
 * @author zzm
 *
 */
public interface DictionaryValueDao extends
		JpaRepository<DictionaryValue, Integer>, JpaSpecificationExecutor<DictionaryValue> {	
	
	/**
	 * 得到数据字典项Id对应的数据字典值分页数据
	 * @param itemId
	 * @param pageable
	 * @return
	 */
	Page<DictionaryValue> findByDictionaryItem_Id(Integer itemId, Pageable pageable);
	
	/**
	 * 得到数据字典项code对应的数据字典值，一般用于页面下拉选择数据	 
	 * @param code
	 * @return
	 */
	List<DictionaryValue> findAllByDictionaryItem_Code(String itemCode);
	
	/**
	 * 新增时检查编码是否重复
	 * @param itemId
	 * @param code
	 * @return
	 */
	Long countByDictionaryItem_IdAndCode(Integer itemId, String code);
	
	/**
	 * 修改时检查编码是否重复
	 * @param itemId
	 * @param code
	 * @param valueId
	 * @return
	 */
	Long countByDictionaryItem_IdAndCodeAndIdNot(Integer itemId, String code, Integer valueId);
	
	/**
	 * 新增时检查值是否重复
	 * @param itemId
	 * @param value
	 * @return
	 */
	Long countByDictionaryItem_IdAndValue(Integer itemId, String value);
	
	/**
	 * 修改时检查值是否重复
	 * @param itemId
	 * @param value
	 * @param valueId
	 * @return
	 */
	Long countByDictionaryItem_IdAndValueAndIdNot(Integer itemId, String value, Integer valueId);

}
