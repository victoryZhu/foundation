package com.yourhealth.common.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yourhealth.common.dao.SelectDao;
import com.yourhealth.common.domain.SelectObject;
import com.yourhealth.common.service.SelectService;

@Service("selectService")     
public class SelectServiceImpl implements SelectService {
	
	//将所有的文档对象放在Hashtable中避免重复读取文件
	private static Map<String, Document> docs = new HashMap<String, Document>();
	
	@Autowired
	private SelectDao selectDao;

	/**
	 * 根据系统id找到对应的配置文件，在根据id得到通用选取信息返回
	 * @param systemId
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@Override
	public SelectObject getSelectObject(String systemId, String id)
			throws IOException, DocumentException {
		Locale locale = LocaleContextHolder.getLocale();				
		Document doc = null;
		String fileName = null;
		
		// 先根据locale找对应的配置文件，格式为selCommConfig_SE_zh_CN.xml
		// 找不到再根据language找对应的配置文件，格式为selCommConfig_SE_zh.xml
		// 最后根据systemId找对应的配置文件，格式selCommConfig_SE.xml
		try{
			fileName = "selCommConfig_"+systemId+"_zh_CN.xml";
			doc = getRootDocument(fileName);	
		}catch(IOException e){
			e.printStackTrace();			
			try{
				fileName = "selCommConfig_"+systemId+"_"+ locale.getLanguage() +".xml";
				doc = getRootDocument(fileName);
			}catch(IOException ex){
				e.printStackTrace();
				fileName = "selCommConfig_"+systemId + ".xml";
				doc = getRootDocument(fileName);
			}
		}		
		
		return getSelectObject(doc.getRootElement(), id);
	}

	/**
	 * 根据通用选取信息，返回查询得到数据list
	 * @param objName
	 * @param listNames
	 * @param pageInfo
	 * @return
	 */
	@Override
	public Page<Map<String, String>> selectDataLists(String objName,
			String[] listNames, Pageable pageable, String condition) {		
		return selectDao.selectDataLists(objName, listNames, pageable, condition);
	}
	
	/**
	 * 读取特定目录下的配置文件，返回Document对象
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	private Document getRootDocument(String fileName) throws IOException,DocumentException {
		//从HashMap中取文档对象
		Document doc = docs.get(fileName);
		
		//如果没有取到则再去读取文件
		if (doc == null) {
			ClassPathResource cr = new ClassPathResource("META-INF/com/chc/common/select/config/"+fileName);
			//创建文档对象
			doc = new SAXReader().read(cr.getFile());
			//将其放入HashMap中以备下次取用
			docs.put(fileName, doc);			
		}
		
		return doc;
	}

	/**
	 * 从Document根元素开始查找指定序号的元素，并返回	 
	 * @param selectElem
	 * @param id
	 * @return
	 */
	private SelectObject getSelectObject(Element selectElem, String id) {		
		
		SelectObject aSelectObject = new SelectObject();
		selectElem = selectElem.elementByID(id);		
		
		//取表属性
		aSelectObject.setObjectPyName(getAttributeValue(selectElem, "pyName"));
		aSelectObject.setObjectAsName(getAttributeValue(selectElem, "asName"));
		aSelectObject.setObjectLgName(getAttributeValue(selectElem, "lgName"));
		aSelectObject.setId(id);
		aSelectObject.setObjectWidth(getAttributeValue(selectElem, "width"));
		aSelectObject.setObjectHeight(getAttributeValue(selectElem, "height"));
		aSelectObject.setOrderName(getAttributeValue(selectElem, "orderName"));
		aSelectObject.setOrderType(getAttributeValue(selectElem, "orderType"));
		
		//取所有的字段属性
		List<?> fields = selectElem.elements("field");
		String[] fieldPyName = new String[fields.size()];
		String[] fieldAsName = new String[fields.size()];
		String[] fieldLgName = new String[fields.size()];
		String[] fieldType = new String[fields.size()];
		String[] fieldValue = new String[fields.size()];
		String[] fieldWidth = new String[fields.size()];
		boolean[] isShow = new boolean[fields.size()];
		boolean[] isSearch = new boolean[fields.size()];		
		
		for (int i = 0; i < fields.size(); i++) {
			Element elem = (Element) fields.get(i);
			fieldPyName[i] = getAttributeValue(elem, "pyName");
			fieldAsName[i] = getAttributeValue(elem, "asName");
			fieldLgName[i] = getAttributeValue(elem, "lgName");
			fieldType[i] = getAttributeValue(elem, "type");
			fieldValue[i] = getAttributeValue(elem, "value");
			isShow[i] = getAttributeValue(elem, "isShow").equals("T");
			isSearch[i] = getAttributeValue(elem, "isSearch").equals("T");		
			fieldWidth[i] = getAttributeValue(elem, "width");
		}
		aSelectObject.setFieldPyName(fieldPyName);
		aSelectObject.setFieldAsName(fieldAsName);
		aSelectObject.setFieldLgName(fieldLgName);
		aSelectObject.setFieldType(fieldType);
		aSelectObject.setFieldValue(fieldValue);
		aSelectObject.setIsShow(isShow);
		aSelectObject.setIsSearch(isSearch);
		aSelectObject.setFieldWidth(fieldWidth);
		
		//子表属性，以后扩展 
		/*List<?> selects = selectElem.elements("table");
		if (selects != null && selects.size() > 0) {
			SelectObject[] arrSelectObject = new SelectObject[selects.size()];
			for (int i = 0; i < selects.size(); i++) {
				arrSelectObject[i] = getSelectObject((Element) selects.get(i),""+i);
			}			
		}*/
				
		return aSelectObject;
	}

	/**
	 * 取元素属性值
	 * @param elem Element
	 * @param attName String
	 * @return String
	 */
	private String getAttributeValue(Element elem, String attName) {		
		String tmp = elem.attributeValue(attName);
		if (tmp == null) {
			return "";
		}
		return tmp;
	}

}
