package com.yourhealth.orgnization.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;

import com.yourhealth.foundation.domain.SystemMenuDataModel;
import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.security.domain.Authorization;

/**
 * 人员信息服务接口类
 * @author zzm
 *
 */
public interface RyxxService {
	
	/** 
	 * 新增人员
	 * 1、人员的所属部门、编号、姓名、状态（新增时为【在职】）是必须信息。 
	 * 2、用户名、编号不能重复。
	 * 3、密码要加密保存
	 * @param ryxx
	 */
	void add(Ryxx ryxx);
	
	/** 
	 * 修改人员
	 * 1、用户名、编号不能重复
	 * 2、更改人员状态为离职：需要删除人员的系统授权。	 
	 * 3、密码不更改
	 * @param ryxx
	 * @return
	 */
	Ryxx update(Ryxx ryxx);
		
	/** 
	 * 根据id得到人员信息
	 * @param ryxxid
	 * @return
	 */
	Ryxx findOne(Integer ryxxid);
	
	/**
	 * 根据username得到人员信息
	 * @param username
	 * @return
	 */
	Ryxx findOneByUsername(String username);
			
	/**
	 * 人员信息分页数据
	 * @param pageable 
	 * @param condition
	 * @return
	 */
	public Page<Ryxx> findAll(Specification<Ryxx> spec, Pageable pageable);	
	
	/**
	 * 人员信息分页数据
	 * @param pageable
	 * @return
	 */
	public Page<Ryxx> findAll(Pageable pageable);	
	
	/**
	 * 根据bmxxId得到人员分页信息
	 * @param bmxxId
	 * @param pageable
	 * @return
	 */
	Page<Ryxx> findAllByBmxxId(Integer bmxxId, Pageable pageable);
		
	/**
	 * 检查公司中人员编号是否已存在
	 * @param opeType
	 * @param ryxx
	 * @return
	 */
	boolean checkRybh(String opeType, Ryxx ryxx);
	
	/**
	 * 检查公司中用户名是否已存在
	 * @param opeType 
	 * @param ryxx
	 * @return
	 */
	boolean checkUsername(String opeType, Ryxx ryxx);	
	
	/**
	 * 检查人员旧密码是否正确
	 * @param ryxxid
	 * @param oldyhkl
	 * @return
	 */
	boolean checkPassword(Integer ryxxid, String oldyhkl);
		
	/**
	 * 修改人员密码
	 * @param id
	 * @param password
	 */
	void updPassword(Integer id, String password);

	/**
	 * 得到人员权限，树形json数据字符串
	 * @param ryxxid
	 * @return
	 */
	String getRyxxFunctionTreeForShow(Integer ryxxid);

	/**
	 * 系统及人员权限信息，用于人员授权
	 * @param ryxxid
	 * @return
	 */
	List<Authorization> getRyxxFunctionTreeForEdit(Integer ryxxid);
	
	/**
	 * 根据ryxxid得到人员权限和角色权限
	 * @param ryxxid
	 * @return
	 */
	Collection<? extends GrantedAuthority> loadAuthorities(Integer ryxxid);

	/**
	 * 根据ryxxid得到人员模块组和模块信息
	 * @param id
	 * @return
	 */
	List<SystemMenuDataModel> getRyxxMenu(Integer ryxxid);		
	
}