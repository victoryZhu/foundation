package com.yourhealth.foundation.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JqGrid查询条件处理类
 * @author zzm
 *
 */
public class JqGridSearchCondition {
	private static Map<String, String> operator = new HashMap<String, String>();  
	private static ObjectMapper mapper = new ObjectMapper(); 
	
	public interface CallBack {  
		public String executeQuery(String f, String o, String d);   
	}   
	
	static {   
	       operator = new HashMap<String, String>();   
	       // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc','nu','nn']   
	       operator.put("eq", " = ");   
	       operator.put("ne", " <> ");   
	       operator.put("lt", " < ");   
	       operator.put("le", " <= ");   
	       operator.put("gt", " > ");   
	       operator.put("ge", " >= ");   
	       operator.put("bw", " LIKE ");   
	       operator.put("bn", " NOT LIKE ");   
	       operator.put("in", " IN ");   
	       operator.put("ni", " NOT IN ");   
	       operator.put("ew", " LIKE ");   
	       operator.put("en", " NOT LIKE ");   
	       operator.put("cn", " LIKE ");   
	       operator.put("nc", " NOT LIKE ");   
	       operator.put("nu", " IS NULL ");   
	       operator.put("nn", " IS NOT NULL ");   
	   } 
	
	/**
	 * 生成一个条件
	 * @param field 字段
	 * @param op   操作
	 * @param data 查询值
	 * @return
	 */
	public static String processOperater(String field, String op, String data) {   
        StringBuffer condition = new StringBuffer();   
        condition.append(field).append(operator.get(op));   
        if (op.equals("in") || op.equals("ni")) {   
            condition.append("(").append(data).append(")");   
        } else if (op.equals("bw") || op.equals("bn")) {   
            condition.append("'").append(data).append("%'");   
        } else if (op.equals("ew") || op.equals("en")) {   
            condition.append("'%").append(data).append("'");   
        } else if (op.equals("cn") || op.equals("nc")) {   
            condition.append("'%").append(data).append("%'");   
        } else if (op.equals("nu") || op.equals("nn")){
        	//
        } else {   
            condition.append("'").append(data).append("'");   
        }   
        return condition.toString();   
    }   
	
	/**
	 * 生成查询条件sql
	 * @param Alias
	 * @param filter
	 * @param callback
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String processSql(String Alias,String filter,CallBack callback) throws Exception {    
		   String prefix  =Alias==null||Alias.equals("")?"":(Alias+".");
	       StringBuffer query = new StringBuffer();   
	       Map<String, Object> _filter = null;   
	       String group = "";   
	       if (filter!=null&&!"".equals(filter)&&(_filter = mapper.readValue(filter, Map.class)) != null) {   
	           group = (String) _filter.get("groupOp");   
	           List<Map<String, String>> rules = (List<Map<String, String>>) _filter.get("rules");  
	           for (Map<String, String> o : rules) {   
	               if(o.get("field")==null||"".equals(o.get("field"))) continue;   
	               String field = o.get("field").trim();   
	               String op = o.get("op").trim();   
	               String data = o.get("data").trim();   
	               String _query = null;   
	               if ((op != null && !"".equals(op.trim()))&& (data != null && !"".equals(data.trim()))) {   
	                      
	                   if (null != callback) { //如果自定义接口，调用接口的处理方法  
	                       _query = callback.executeQuery(prefix+field, op, data);   
	                   }   
	                   if (_query!=null&&_query.length()!=0) {   
	                	   
	                   } else {   
	                	   _query = processOperater(prefix+field, op, data);
	                   }
	                   query.append(" ").append(group).append(" ").append(_query);
	               }   
	            }   
	        }  
	       processGroup(group,query,false); 
	       return query.toString();
	}
	
    /**
     * 处理Group   
     * @param group
     * @param conditions
     * @param isSub
     * @return
     */
    private static StringBuffer processGroup(String group,StringBuffer conditions,boolean isSub){   
        if("OR".equals(group)&&conditions.length()!=0&&!isSub){   
        	//选择的查询条件只会是and和or中间的一种，
        	//当是or的时候，输入条件之间是or，但是和其他条件之间是and关系，需要进行转换，
        	//当时and的时侯不需要转换
            conditions.replace(1, group.length()+2,"AND (").append(")");   
        }else if("OR".equals(group)&&conditions.length()>12&&isSub){ 
        	//isSub＝false，下面的不会执行。
            conditions.replace(13, 14+group.length(), "AND (").append(")");   
        }   
        return conditions;              
    } 
    
}