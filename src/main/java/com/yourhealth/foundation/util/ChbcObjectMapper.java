package com.yourhealth.foundation.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class ChbcObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	
	public ChbcObjectMapper () {
		JodaModule jModule = new JodaModule();
		this.registerModule(jModule);
		Hibernate4Module hModule = new Hibernate4Module();
		hModule.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
		this.registerModule(hModule);
		this.registerModule(new Jdk7Module());
		this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		this.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//this.setDateFormat(new ISO8601DateFormat()); 
		this.setTimeZone(TimeZone.getDefault());
	}

}
