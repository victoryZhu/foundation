package com.yourhealth.foundation.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SpecificationByJqgridFilters<T> implements Specification<T> {
	private JqgridFiltersModel criteria;
	
	public JqgridFiltersModel getCriteria() {
		return this.criteria;
	}
	
	public void setFilters(JqgridFiltersModel criteria) {
		this.criteria = criteria;
	}
	
	public SpecificationByJqgridFilters(String filters) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JqgridFiltersModel criteria = mapper.readValue(filters, JqgridFiltersModel.class);
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return processFilters(this.criteria, root, query, cb);
	}
	
	@SuppressWarnings("unchecked")
	private Predicate processFilters(JqgridFiltersModel criteria, Root<T> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		String groupOp = criteria.groupOp;
		Predicate predicate = groupOp.equalsIgnoreCase("AND")? cb.conjunction():cb.disjunction();
		for (JqgridFiltersRule rule:criteria.rules) {
			String[] field = rule.field.split("\\.");
			Path<?> path = root;
			for (String f:field) {
				path = path.get(f);
			}
			switch(rule.op) {
				case "eq": {
					Predicate p = cb.equal(path, rule.data);
					predicate = groupOp.equalsIgnoreCase("AND")? cb.and(predicate,p):cb.or(predicate,p);
					break;
				}
				case "ne": {
					Predicate p = cb.notEqual(path, rule.data);
					predicate = groupOp.equalsIgnoreCase("AND")? cb.and(predicate,p):cb.or(predicate,p);
					break;
				}
				case "cn": {
					Predicate p = cb.like((Expression<String>)path, "%"+rule.data+"%");
					predicate = groupOp.equalsIgnoreCase("AND")? cb.and(predicate,p):cb.or(predicate,p);
					break;
				}
				case "nc": {
					Predicate p = cb.notLike((Expression<String>)path, "%"+rule.data+"%");
					predicate = groupOp.equalsIgnoreCase("AND")? cb.and(predicate,p):cb.or(predicate,p);
					break;
				}
			}				
		}
		if (criteria.groups != null) {
			for (JqgridFiltersModel f:criteria.groups) {
				Predicate p = processFilters(f, root, query, cb);
				predicate = groupOp.equalsIgnoreCase("AND")? cb.and(predicate,p):cb.or(predicate,p);
			}			
		}
		return  predicate;
	}
}
