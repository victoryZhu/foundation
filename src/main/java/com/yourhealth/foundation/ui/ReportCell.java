package com.yourhealth.foundation.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class ReportCell {
		private String propertyName;
		private int width;
		private String cnName;
		private String colType;
		public String pattern = "yyyy-MM-dd";
		
		public ReportCell(String propertyName,String cnName,int width,String colType,String pattern){
			setPropertyName(propertyName);
			setCnName(cnName);
			setWidth(width);
			setColType(colType);
			setPattern(pattern);
		}
		
		public ReportCell(String propertyName,String cnName,int width,String colType){
			setPropertyName(propertyName);
			setCnName(cnName);
			setWidth(width);
			setColType(colType);
			if(colType.toLowerCase().equals("Calendar")){
				setPattern(pattern);
			}else{
				
			}
		}	

		public ReportCell(String propertyName,String cnName,int width){
			setPropertyName(propertyName);
			setCnName(cnName);
			setWidth(width);
			setColType("String");
		}
		
		public ReportCell(String propertyName,String cnName){
			setPropertyName(propertyName);
			setCnName(cnName);
			setWidth(20);
			setColType("String");
		}
		
		public String format(Object propValue) {
			java.util.Date date = null; 
			SimpleDateFormat format = new SimpleDateFormat(pattern);
	        //TODO 其他类型的格式化
	        if("Calendar".equalsIgnoreCase(colType)){
	        	Calendar c = (Calendar) propValue;	
	        	date = c.getTime();	        	
	        }else if("Date".equalsIgnoreCase(colType)){
	        	date = (Date) propValue;
	        }
	        
	        return format.format(date);
	    }
	    
	    public String getCnName() {
	        return cnName;
	    }

	    public void setCnName(String cnName) {
	        this.cnName = cnName;
	    }

	    public String getPropertyName() {
	        return propertyName;
	    }
	    
	    public void setPropertyName(String propertyName) {
	        this.propertyName = propertyName;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public void setWidth(int width) {
	        this.width = width;
	    }
	    
		public String getColType() {
			return colType;
		}
		
		public void setColType(String colType) {
			this.colType = colType;
		}
		
		public String getPattern() {
			return pattern;
		}
		
		public void setPattern(String pattern) {
			this.pattern = pattern;
		}
}