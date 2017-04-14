package com.yourhealth.orgnization.dao;

import java.util.List;

public interface RyxxCustomDao {
	
	/**
	 * 根据人员授权和角色授权得到人员全部的权限信息，返回模块组、模块和功能的ID、编号和名称
	 * @param ryxxid
	 * @return
	 */
	public List<?> queryRyxxFunctionLists(Integer ryxxid);

}
