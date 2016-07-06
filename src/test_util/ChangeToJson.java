package test_util;

import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class ChangeToJson {
	
	
	public static String mapToJson(Map<String, Object> map) {
		
		String jsonString = "";
		String temString = "";
		
		for (Entry<String, Object> entry : map.entrySet()) {  
			  
		    String key = entry.getKey();
		    String value = entry.getValue().toString();
		    System.out.println("Key = " + key+ ", Value = " + value); 
		  
		    temString += (key + ":" + value + ",");
		    System.out.println(temString);
		    
		} 
		
		jsonString = "{"+temString.substring(0, temString.length()-1)+"}";

		return jsonString;
	}
	
	@Test
	public void stringTest() {
		
		String temString = "key1:value1,key2:value,";
		
		String jsonString = "{"+temString.substring(0, temString.length()-1)+"}";
		
		System.out.println(jsonString);
		
		
	}

}
