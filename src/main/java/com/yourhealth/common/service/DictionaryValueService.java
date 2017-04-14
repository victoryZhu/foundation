package com.yourhealth.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yourhealth.common.domain.DictionaryValue;

/**
 * 数据字典值Service接口类
 * @author zzm
 *
 */
public interface DictionaryValueService {

	/**
	 * 根据数据字典项id和分页条件得到数据字典值List
	 * @param itemId
	 * @param pageable
	 * @return
	 */
	Page<DictionaryValue> findByDictionaryItem_Id(Integer itemId, Pageable pageable);
	
	/**
	 * 根据数据字典项code得到数据字典值，按照序号排序
	 * @param code
	 * @return
	 */
	List<DictionaryValue> findByItemCode(String itemCode);
	
	/**
	 * 根据Id得到一个数据字典值
	 * @param id
	 * @return
	 */
	DictionaryValue findOne(Integer id);

	/**
	 * 保存一个数据字典值
	 * @param dictionaryValue
	 * @return
	 */
	DictionaryValue save(DictionaryValue dictionaryValue);
	
	/**
	 * 删除一个数据字典值
	 * @param dictionaryValue
	 */
	void delete(DictionaryValue dictionaryValue);
	
	/**
	 * 删除一个数据字典值
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 检查同一数据字典项的数据字典的编号是否重复
	 * @param opeType 表示操作，新增：ADD/修改：UPD
	 * @param dictionaryValue
	 * @return
	 */
	boolean chkDictionaryValueCode(String opeType, DictionaryValue dictionaryValue);

	/**
	 * 检查同一数据字典项的数据字典的值是否重复
	 * @param opeType  表示操作，新增：ADD/修改：UPD
	 * @param dictionaryValue
	 * @return
	 */
	boolean chkDictionaryValueValue(String opeType, DictionaryValue dictionaryValue);

}