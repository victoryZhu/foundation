package com.yourhealth.orgnization.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yourhealth.deploy.dao.FunctionDao;
import com.yourhealth.deploy.domain.Function;
import com.yourhealth.foundation.domain.SystemMenuDataModel;
import com.yourhealth.orgnization.dao.RyxxDao;
import com.yourhealth.orgnization.domain.Ryxx;
import com.yourhealth.orgnization.service.RyxxService;
import com.yourhealth.security.domain.Authorization;
import com.yourhealth.security.domain.RyxxFunction;
import com.yourhealth.security.service.RoleRyxxService;
import com.yourhealth.security.service.RoleService;
import com.yourhealth.security.service.RyxxFunctionService;

/**
 * 人员信息服务实现类
 * @author zzm
 *
 */
@Service("ryxxService")
@Transactional(propagation = Propagation.REQUIRED)
public class RyxxServiceImpl implements RyxxService {
	
	//private static final Logger logger = LoggerFactory.getLogger(RyxxServiceImpl.class);	
	
	@Autowired
	private RyxxDao ryxxDao;	
	@Autowired
	private RoleRyxxService roleRyxxService;	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RyxxFunctionService ryxxFunctionService;	
	@Autowired
	private FunctionDao functionDao;
	private String preRole = "";   //使用@Secured注解前缀必须是ROLE_，现在使用@PreAuthorize
	@Autowired
    private PasswordEncoder passwordEncoder;
		
	@Override
	public void add(Ryxx ryxx) {
		ryxx.setPassword(passwordEncoder.encode(ryxx.getPassword()));
		ryxxDao.save(ryxx);		
	}

	@Override
	public Ryxx update(Ryxx ryxx) {							
		// 状态更改为离职		
		if(!ryxx.isEmployed()){
			// 删除人员所有的用户权限模块功能
			ryxxFunctionService.delRyxxFunction(ryxx);						
			// 删除人员的所有角色人员信息
			roleRyxxService.deleteByRyxxId(ryxx.getId());
		}		
			
		return ryxxDao.save(ryxx);	
	}

	@Override
	public Ryxx findOne(Integer ryxxid) {		
		return ryxxDao.findOne(ryxxid);
	}
	
	@Override
	public Ryxx findOneByUsername(String username) {
		return ryxxDao.findOneByUsername(username);
	}
	
	@Override
	public Page<Ryxx> findAll(Specification<Ryxx> spec, Pageable pageable) {
		return ryxxDao.findAll(spec, pageable);
	}
	
	@Override
	public Page<Ryxx> findAll(Pageable pageable) {
		return ryxxDao.findAll(pageable);
	}	
	
	@Override
	public Page<Ryxx> findAllByBmxxId(Integer bmxxId, Pageable pageable) {		
		return ryxxDao.findAllByBmxxId(bmxxId, pageable);
	}

	@Override
	public boolean checkRybh(String opeType, Ryxx ryxx) {
		Long num = 0L;
		if("ADD".equals(opeType)){
			num = ryxxDao.countByRybh(ryxx.getRybh()); 
		}else{
			num = ryxxDao.countByRybhAndIdNot(ryxx.getRybh(), ryxx.getId()); 
		}
		
		return num > 0?true:false;
	}	
	
	@Override
	public boolean checkUsername(String opeType, Ryxx ryxx) {
		Long num = 0L;
		if("ADD".equals(opeType)){
			num = ryxxDao.countByUsername(ryxx.getUsername()); 
		}else{
			num = ryxxDao.countByUsernameAndIdNot(ryxx.getUsername(), ryxx.getId()); 
		}
		
		return num > 0?true:false;	
	}
	
	@Override
	public boolean checkPassword(Integer ryxxid, String oldyhkl) {
		return passwordEncoder.matches(oldyhkl, findOne(ryxxid).getPassword());
	}

	@Override
	public void updPassword(Integer id, String password) {		
		ryxxDao.updPassword(id, passwordEncoder.encode(password));
	}

	@Override
	public String getRyxxFunctionTreeForShow(Integer ryxxId) {
		StringBuffer retString = new StringBuffer("[");
		String categoryCode = "";
		String moduleCode = "";
		List<RyxxFunction> rfList = ryxxFunctionService.findAllByRyxxIdOrderByFunctionCodeAsc(ryxxId);
		for(RyxxFunction rf : rfList){
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
	public List<Authorization> getRyxxFunctionTreeForEdit(Integer ryxxId) {		
		// 记录用户已有权限，页面显示为选中状态		
		List<RyxxFunction> rfList = ryxxFunctionService.findAllByRyxxIdOrderByFunctionCodeAsc(ryxxId);
		Map<String, Map<String, String>> mapFunctions = new HashMap<String, Map<String, String>>();
		for(RyxxFunction rf : rfList){				
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

	@Override
	public Collection<? extends GrantedAuthority> loadAuthorities(Integer ryxxid) {
		List<GrantedAuthority> retList = new ArrayList<GrantedAuthority>();		
		List<?> functions = ryxxDao.queryRyxxFunctionLists(ryxxid);
		
		for(int i=0; i<functions.size(); i++){
			//if(logger.isDebugEnabled()) 
				//logger.debug("function.id = " + function.getId() + ", function.code = " + function.getCode());
			Object[] row = (Object[]) functions.get(i);
			retList.add(new SimpleGrantedAuthority(preRole+row[9]));
			retList.add(new SimpleGrantedAuthority(preRole+row[1]));
		}
		
		return retList;
	}

	@Override
	public List<SystemMenuDataModel> getRyxxMenu(Integer ryxxid) {
		List<SystemMenuDataModel> menu = new ArrayList<SystemMenuDataModel>();
		String _categoryCode = "";
		String _moduleCode = "";
		List<?> functions = ryxxDao.queryRyxxFunctionLists(ryxxid);
		for(int i=0; i<functions.size(); i++){			
			Object[] row = (Object[]) functions.get(i);
			String categoryCode = row[1].toString();
			String categoryName = row[2].toString();
			String moduleCode = row[4].toString();			
			String moduleName = row[5].toString();	
			String moduleUrl = row[6].toString();
			
			if(!_categoryCode.equalsIgnoreCase(categoryCode)){
				SystemMenuDataModel sm = new SystemMenuDataModel(categoryCode, categoryName, "", SystemMenuDataModel.Type.CATEGORY, "");
				menu.add(sm);
				_categoryCode = categoryCode;				
			}
			
			if(!_moduleCode.equalsIgnoreCase(moduleCode)){
				SystemMenuDataModel sm = new SystemMenuDataModel(moduleCode, moduleName, moduleUrl, SystemMenuDataModel.Type.MODULE, _categoryCode);
				menu.add(sm);
				_moduleCode = moduleCode;				
			}			
		}		
		
		return menu;
	}		
	
}