package com.yourhealth.deploy.service;

/**
 * 模块service接口类
 * @author zzm
 *
 */

import com.yourhealth.deploy.domain.Module;

public interface ModuleService {
	
	Module save(Module module);
	
	void delete(Module module);
	
	void delete(Integer id);
	
	Module findOne(Integer id);
	
	/** 
	 * 检查模块节点能否删除
	 * @param id
	 * @return
	 */
	boolean canDelModule(Integer id);
	
	/**
	 * 检查模块节点能否删除
	 * @param module
	 * @return
	 */
	boolean canDelModule(Module module);

}
