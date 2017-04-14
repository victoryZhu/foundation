package com.yourhealth.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.common.dao.DictionaryItemDao;
import com.yourhealth.common.domain.DictionaryItem;
import com.yourhealth.common.service.DictionaryItemService;

/**
 * 数据字典项Service实现类
 * @author zzm
 * @param <DictionaryItemDao>
 *
 */
@Service("dictionaryItemService")        
@Transactional(propagation = Propagation.REQUIRED)
public class DictionaryItemServiceImpl implements DictionaryItemService {

	@Autowired
	private DictionaryItemDao dictionaryItemDao;
	
	@Override
	public Page<DictionaryItem> findAll(Specification<DictionaryItem> spec,
			Pageable pageable) {		
		return dictionaryItemDao.findAll(spec, pageable);
	}

	@Override
	public Page<DictionaryItem> findAll(Pageable pageable) {		
		return dictionaryItemDao.findAll(pageable);
	}

	@Override
	public DictionaryItem save(DictionaryItem dictionaryItem) {
		return dictionaryItemDao.save(dictionaryItem);
	}

	@Override
	public boolean chkItemName(DictionaryItem dictionaryItem) {		
		return dictionaryItemDao.countByNameAndIdNot(dictionaryItem.getName(), dictionaryItem.getId())==0?false:true;
	}	

}
