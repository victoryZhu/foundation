package com.yourhealth.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.deploy.dao.FunctionDao;
import com.yourhealth.deploy.domain.Function;
import com.yourhealth.security.dao.RoleDao;
import com.yourhealth.security.domain.Authorization;
import com.yourhealth.security.domain.Role;
import com.yourhealth.security.domain.RoleFunction;
import com.yourhealth.security.service.RoleFunctionService;
import com.yourhealth.security.service.RoleService;

/**
 * 角色实现类
 * @author zzm
 *
 */
@Service("roleService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;	
	@Autowired
	private RoleFunctionService roleFunctionService;
	@Autowired
	private FunctionDao functionDao;
		
	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void delete(Integer roleid) {
		roleDao.delete(roleid);
	}
			
	@Override
	public Role findOne(Integer roleid) {
		return roleDao.findOne(roleid);
	}
	
	@Override
	public Page<Role> findAll(Specification<Role> spec, Pageable pageable) {		
		return roleDao.findAll(spec, pageable);
	}	
	
	@Override
	public boolean checkRoleCode(String opeType, Role role) {	
		if("ADD".equals(opeType))			
			return roleDao.countByCode(role.getCode())>0?true:false;	
		else
			return roleDao.countByCodeAndIdNot(role.getCode(), role.getId())>0?true:false;				
	}

	@Override
	public boolean checkRoleName(String opeType, Role role) {
		if("ADD".equals(opeType))			
			return roleDao.countByName(role.getName())>0?true:false;	
		else
			return roleDao.countByNameAndIdNot(role.getName(), role.getId())>0?true:false;			
	}

	@Override
	public String getRoleFunctionTreeForShow(Integer roleId) {
		StringBuffer retString = new StringBuffer("[");
		String categoryCode = "";
		String moduleCode = "";
		List<RoleFunction> rfList = roleFunctionService.findAllByRoleIdOrderByFunctionCodeAsc(roleId);
		for(RoleFunction rf : rfList){
			if(!categoryCode.equals(rf.getFunction().getModule().getCategory().getCode())){
				categoryCode = rf.getFunction().getModule().getCategory().getCode();
				retString.append("{\"id\":\"").append(categoryCode).append("\", \"pId\":\"0\", \"name\":\"【").append(categoryCode).append("】").append(rf.getFunction().getModule().getCategory().getName()).append("\"},");
			}
			if(!moduleCode.equals(rf.getFunction().getModule().getCode())){
				moduleCode = rf.getFunction().getModule().getCode();
				retString.append("{\"id\":\"").append(moduleCode).append("\", \"pId\":\"").append(categoryCode).append("\", \"name\":\"【").append(moduleCode).append("】").append(rf.getFunction().getModule().getName()).append("\"},");
			}					
			retString.append("{\"id\":\"").append(rf.getFunction().getCode()).append("\", \"pId\":\"").append(moduleCode).append("\", \"name\":\"【").append(rf.getFunction().getCode()).append("】").append(rf.getFunction().getName()).append("\"},");
		}
		
		if(retString.length()>1)
			retString.deleteCharAt(retString.length()-1);
		retString.append("]");
		
		return retString.toString();
	}

	@Override
	public List<Authorization> getRoleFunctionTreeForEdit(Integer roleId) {
		// 记录用户已有权限，页面显示为选中状态		
		List<RoleFunction> rfList = roleFunctionService.findAllByRoleIdOrderByFunctionCodeAsc(roleId);
		Map<String, Map<String, String>> mapFunctions = new HashMap<String, Map<String, String>>();
		for(RoleFunction rf : rfList){				
			Map<String, String> map = new HashMap<String, String>();
			map.put("idx", rf.getId()+"");			
			mapFunctions.put(rf.getFunction().getCode(), map);
		}
				
		//取所有功能信息
		List<Function> listFunctions = functionDao.findAllByOrderByModule_Category_CodeAscModule_CodeAscCodeAsc(); 
		return creFunctionTreeForEdit(listFunctions , mapFunctions);
	}
	
	/**
	 * 
	 * @param listFunctions 数据依次为模组编号、模组名称、模块编号、模块名称、功能编号、功能名称
	 * @param mapFunctions 是已设置的功能信息id	
	 * @return
	 */
	private List<Authorization> creFunctionTreeForEdit(List<Function> listFunctions, Map<String, Map<String, String>> mapFunctions) {			
		List<Authorization> datas = new ArrayList<Authorization>();
		String modGroupId = "";
		String moduleId = "";
			
		//循环处理系统部署设定的功能范围
		for (int i = 0; i < listFunctions.size(); i++) {
			Authorization data;
			Function func = listFunctions.get(i);
				
			//循环处理每个功能，当功能对应的模块组和模块不存在是放入map，要求功能是排好顺序的
			String s_modGroupId = func.getModule().getCategory().getCode();
			String s_moduleId = func.getModule().getCode();
			//模块组
			if (!modGroupId.equals(s_modGroupId)) {						
				data = new Authorization();
				data.setId(s_modGroupId);
				data.setFunctionname("【"+func.getModule().getCategory().getCode()+"】"+func.getModule().getCategory().getName());
				data.setLevel("0");
				data.setParent("");
				data.setIsLeaf("false");
	
				datas.add(data);
				modGroupId = s_modGroupId;
			}
			//模块
			if (!moduleId.equals(s_moduleId)) {				
				data = new Authorization();
				data.setId(s_moduleId);
				data.setFunctionname("【"+func.getModule().getCode()+"】"+func.getModule().getName());
				data.setLevel("1");
				data.setParent(s_modGroupId);
				data.setIsLeaf("false");

				datas.add(data);
				moduleId = s_moduleId;
			}
			//功能			
			data = new Authorization();
			data.setId(func.getCode());
			data.setFunctionname("【"+func.getCode()+"】"+func.getName());
			if(mapFunctions.containsKey(func.getCode())){
				data.setEnbl("1");							
			}else{
				data.setEnbl("0");				
			}	
			data.setIdx(func.getId()+"");
			data.setLevel("2");
			data.setParent(s_moduleId);
			data.setIsLeaf("true");
	
			datas.add(data);
		}
			
		return datas;
	}
}