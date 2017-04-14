package com.yourhealth.common.service;

import org.springframework.data.domain.Sort;

public interface ChbcUtils {
	/**
	 * 
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public Sort getSortBySidxAndSord(String sidx, String sord);
	/**
	 * 
	 * @param mimetype
	 * @return
	 */
	public String getFiletypeByMimetype(String mimetype);
}
