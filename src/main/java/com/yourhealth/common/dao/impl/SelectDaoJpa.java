package com.yourhealth.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.yourhealth.common.dao.SelectDao;

/**
 * 通用选取DAO实现类
 * @author zzm
 *
 */
@Repository("selectDao")
public class SelectDaoJpa implements SelectDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Map<String, String>> selectDataLists(String objName, String[] listNames, Pageable pageable, String condition) {
		
		Long records = (Long) em.createQuery("select count(*) from " + objName + condition).getSingleResult();
		pageable = getMaxPageable(pageable, records);
		
		StringBuffer fieldNames = new StringBuffer("");
		for(String fieldName : listNames){
			fieldNames.append(fieldName).append(",");
		}
		fieldNames.deleteCharAt(fieldNames.length()-1);
		
		List<?> result = em.createQuery("select " + fieldNames + " from " + objName + condition + getOrderString(pageable))
				.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber()))
				.setMaxResults(	pageable.getPageSize())	
				.getResultList();
		
		//将查询的数据放入Map，在controller中转为json格式返回调用页面
		List<Map<String,String>> datas = new ArrayList<Map<String,String>>();				
		for(int i=0; i<result.size(); i++){
			Map<String,String> data = new HashMap<String,String>();
			Object[] values = (Object[]) result.get(i);
			for(int j=0; j<listNames.length; j++){
				data.put(listNames[j], values[j]==null?"":values[j].toString());				
			}
			datas.add(data);
		}
				
		return new PageImpl<>(datas, pageable, records);		
	}	
	
	//根据总记录数计算当前页数
	protected Pageable getMaxPageable(Pageable pageable, Long records) {
		int firstRowNumberInPage = pageable.getOffset() + 1;
		if (firstRowNumberInPage == 1 || records == 0) {
			return pageable.first();
		} else if (firstRowNumberInPage > records) {
			return new PageRequest((int) Math.floor((records-1)/pageable.getPageSize()), pageable.getPageSize(), pageable.getSort());
		} else {
			return pageable;
		}
	}		
			
	//得到排序条件
	protected String getOrderString(Pageable pageable) {
		String sqlOrder;
		Sort sort = pageable.getSort();
		if (sort == null) {
			sqlOrder = "";
		} else {
			sqlOrder = " ORDER BY ";
			Iterator<Order> order = sort.iterator();
			while (order.hasNext()) {
				Order o = order.next();
				sqlOrder = sqlOrder + o.getProperty() + " " + o.getDirection() + ",";
			}
			sqlOrder = sqlOrder.substring(0, sqlOrder.length()-1);
		}
		return sqlOrder;
	}		
	
}