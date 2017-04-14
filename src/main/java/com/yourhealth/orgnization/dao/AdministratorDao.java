package com.yourhealth.orgnization.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yourhealth.orgnization.domain.Administrator;

public interface AdministratorDao extends JpaRepository<Administrator, Integer>,
		JpaSpecificationExecutor<Administrator> {
	
	/**
	 * 根据username得到管理员信息
	 * @param username
	 * @return
	 */
	Administrator findOneByUsername(String username);

	/**
	 * 修改用户密码
	 * @param id
	 * @param password
	 */
	@Modifying
	@Query("update Administrator a set a.password = ?2 where a.id = ?1")
	void updPassword(Integer id, String password);

}
