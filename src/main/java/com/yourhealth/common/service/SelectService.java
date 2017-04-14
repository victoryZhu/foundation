package com.yourhealth.common.service;

import java.io.IOException;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yourhealth.common.domain.SelectObject;

public interface SelectService {
	
	/**
	 * 根据系统id找到对应的配置文件，在根据id得到通用选取信息返回
	 * @param systemId
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public SelectObject getSelectObject(String systemId, String id) throws IOException, DocumentException;
	
	/**
	 * 根据通用选取信息，返回查询得到数据list
	 * @param objName
	 * @param listNames
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String, String>> selectDataLists(String objName, String[] listNames, Pageable pageable, String condition);	

}