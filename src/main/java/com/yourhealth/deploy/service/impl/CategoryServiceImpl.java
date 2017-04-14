package com.yourhealth.deploy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.deploy.dao.CategoryDao;
import com.yourhealth.deploy.dao.FunctionDao;
import com.yourhealth.deploy.dao.ModuleDao;
import com.yourhealth.deploy.domain.Category;
import com.yourhealth.deploy.domain.Function;
import com.yourhealth.deploy.domain.Module;
import com.yourhealth.deploy.service.CategoryService;

/**
 * 模块组service实现类
 * @author zzm
 *
 */
@Service("categoryService")
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private FunctionDao functionDao;
	
	@Override
	public Category save(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	@Override
	public void delete(Integer id) {
		categoryDao.delete(id);
	}

	@Override
	public Category findOne(Integer id) {		
		return categoryDao.findOne(id);
	}

	@Override
	public boolean canDelCategory(Integer id) {
		return canDelCategory(findOne(id));
	}

	@Override
	public boolean canDelCategory(Category category) {		
		if(category.getModules().size()>0) 
			return false;
		else
			return true;
	}

	@Override
	public String queryAllJsonCategorys() {
		StringBuffer retString = new StringBuffer("[");
		List<Category> categoryLists = categoryDao.findAllByOrderByCodeAsc(); 
		List<Module> moduleLists = null;
		List<Function> functionLists = null;

		for(Category category : categoryLists){			
			retString.append("{\"id\":").append(category.getId()*10000).append(", \"pId\":0, \"name\":\"【").append(category.getCode()).append("】").append(category.getName()).append("\"},");
						
			moduleLists = moduleDao.findAllByCategory_IdOrderByCodeAsc(category.getId());
			for(Module module : moduleLists){
				retString.append("{\"id\":").append(module.getId()*100).append(", \"pId\":").append(category.getId()*10000).append(", \"name\":\"【").append(module.getCode()).append("】").append(module.getName()).append("\"},");
								
				functionLists = functionDao.findAllByModule_IdOrderByCodeAsc(module.getId()); 
				for(Function function : functionLists){
					retString.append("{\"id\":").append(function.getId()).append(", \"pId\":").append(module.getId()*100).append(", \"name\":\"【").append(function.getCode()).append("】").append(function.getName()).append("\"},");					
				}
			}			
		}
		if(retString.length()>1)
			retString.deleteCharAt(retString.length()-1);
		retString.append("]");
		
		return retString.toString();
	}

}
