package com.yourhealth.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.common.dao.DictionaryValueDao;
import com.yourhealth.common.domain.DictionaryValue;
import com.yourhealth.common.service.DictionaryValueService;

/**
 * 数据字典值Service实现类
 * @author zzm
 *
 */
@Service("dictionaryValueService")        
@Transactional(propagation = Propagation.REQUIRED)
public class DictionaryValueServiceImpl implements DictionaryValueService {

	@Autowired
	private DictionaryValueDao dictionaryValueDao;
	
	@Override
	public DictionaryValue findOne(Integer id) {		
		return dictionaryValueDao.findOne(id);
	}
		
	@Override
	public Page<DictionaryValue> findByDictionaryItem_Id(Integer itemId, Pageable pageable) {
		return dictionaryValueDao.findByDictionaryItem_Id(itemId, pageable);				
	}
	
	@Override
	public List<DictionaryValue> findByItemCode(String itemCode) {		
		return dictionaryValueDao.findAllByDictionaryItem_Code(itemCode);
	}	

	@Override
	public DictionaryValue save(DictionaryValue dictionaryValue) {
		 return dictionaryValueDao.save(dictionaryValue);
	}

	@Override
	public void delete(DictionaryValue dictionaryValue) {
		dictionaryValueDao.delete(dictionaryValue);		
	}

	@Override
	public void delete(Integer id) {
		dictionaryValueDao.delete(id);
	}

	@Override
	public boolean chkDictionaryValueCode(String opeType, DictionaryValue dictionaryValue) {
		Long rows = 0L;
		
		if("ADD".equalsIgnoreCase(opeType)){
			rows = dictionaryValueDao.countByDictionaryItem_IdAndCode(dictionaryValue.getDictionaryItem().getId(), dictionaryValue.getCode());
		}else{
			rows = dictionaryValueDao.countByDictionaryItem_IdAndCodeAndIdNot(dictionaryValue.getDictionaryItem().getId(), dictionaryValue.getCode(), dictionaryValue.getId());
		}
		
		return rows==0?false:true;
	}

	@Override
	public boolean chkDictionaryValueValue(String opeType, DictionaryValue dictionaryValue) {
		Long rows = 0L;
		
		if("ADD".equalsIgnoreCase(opeType)){
			rows = dictionaryValueDao.countByDictionaryItem_IdAndValue(dictionaryValue.getDictionaryItem().getId(), dictionaryValue.getValue());
		}else{
			rows = dictionaryValueDao.countByDictionaryItem_IdAndValueAndIdNot(dictionaryValue.getDictionaryItem().getId(), dictionaryValue.getValue(), dictionaryValue.getId());
		}
		
		return rows==0?false:true;
	}	

}
