package com.yourhealth.orgnization.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yourhealth.orgnization.domain.Ryxx;

public interface RyxxDao extends JpaRepository<Ryxx, Integer>, RyxxCustomDao,
		JpaSpecificationExecutor<Ryxx> {
	
	/**
	 * 根据username得到人员信息
	 * @param username
	 * @return
	 */
	Ryxx findOneByUsername(String username);
	
	Long countByRybh(String rybh);
	
	Long countByRybhAndIdNot(String rybh, Integer id);
	
	Long countByUsername(String username);
	
	Long countByUsernameAndIdNot(String username, Integer id);
	
	/**
	 * 根据bmxxId得到人员信息的分页数据
	 * @param bmxxId
	 * @param pageable
	 * @return
	 */
	Page<Ryxx> findAllByBmxxId(Integer bmxxId, Pageable pageable);	                    

	/**
	 * 修改用户密码
	 * @param id
	 * @param password
	 */
	@Modifying
	@Query("update Ryxx r set r.password = :password where r.id = :id")
	void updPassword(@Param("id") Integer id, @Param("password") String password);

}
