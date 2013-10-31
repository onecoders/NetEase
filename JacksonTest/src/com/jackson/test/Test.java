package com.jackson.test;

/*import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Column column = new Column();
		column.set_id(12).setColumn_1(22).setColumn_2("name")
				.setColumn_3("gender").setColumn_4("student");
		long before = System.currentTimeMillis();
		/*ObjectMapper mapper = JacksonUtil.getInstance();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, column);
			gen.close();
			String json = sw.toString();
			System.out.println(json);

			Column newColumn = mapper.readValue(json, Column.class);
			System.out.println(newColumn.toString());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		String json = JacksonUtil.parse2Json(column);
		System.out.println(json);
		System.out.println(((Column)JacksonUtil.parse2Bean(json, Column.class)).toString());
		System.out.println(System.currentTimeMillis() - before);
	}

}
