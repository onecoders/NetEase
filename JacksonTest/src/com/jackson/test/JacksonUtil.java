package com.jackson.test;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonUtil {

	private static ObjectMapper mapper;

	private JacksonUtil() {

	}

	public static synchronized ObjectMapper getInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static String parse2Json(Object object){
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			getInstance().writeValue(gen, object);
			gen.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public static Object parse2Bean(String json, Class<?> valueType){
		try {
			return getInstance().readValue(json, valueType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
