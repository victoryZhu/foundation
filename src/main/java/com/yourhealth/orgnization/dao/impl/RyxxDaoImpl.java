package com.yourhealth.orgnization.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.yourhealth.orgnization.dao.RyxxCustomDao;

public class RyxxDaoImpl implements RyxxCustomDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<?> queryRyxxFunctionLists(Integer ryxxid) {
		return (List<?>)em.createNativeQuery("select c.id as categoryid, c.code as categorycode, c.name as categoryname, m.id as moduleid, m.code as modulecode, "
				+ "m.name as modulename, m.moduleurl, f.id as functionid, f.code as functioncode, f.name as functionname from t_category c, t_module m, t_function f "
				+ "where c.id = m.categoryid and m.id = f.moduleid and f.id in (select functionid from t_ryxxfunction where ryxxid = :ryxxid union "
				+ "select functionid from t_rolefunction where roleid in (select roleid from t_roleryxx where ryxxid = :ryxxid)) order by c.code, m.code, f.code")
				.setParameter("ryxxid", ryxxid).getResultList();
	}

}
