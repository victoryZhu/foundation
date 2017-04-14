package com.yourhealth.orgnization.service;

import com.yourhealth.orgnization.domain.Administrator;

/**
 * 管理员信息服务接口类
 * @author zzm
 *
 */
public interface AdministratorService {
	
	/**
	 * 修改管理员
	 * @param Administrator
	 * @return
	 */
	Administrator save(Administrator administrator);

	/**
	 * 根据id得到管理员信息
	 * @param id
	 * @return
	 */
	Administrator findOne(Integer id);
	
	/**
	 * 根据username得到管理员信息
	 * @param username
	 * @return
	 */
	Administrator findOneByUsername(String username);

	/**
	 * 检查管理员密码
	 * @param id
	 * @param oldyhkl
	 * @return
	 */
	boolean checkPassword(Integer id, String oldyhkl);

	/**
	 * 修改管理员密码
	 * @param id
	 * @param password
	 */
	void updPassword(Integer id, String password);	
	
}