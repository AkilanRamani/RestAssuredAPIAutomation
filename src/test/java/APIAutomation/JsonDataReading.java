package APIAutomation;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;

import io.restassured.path.json.JsonPath;

public class JsonDataReading {

	public static void main(String[] args) throws IOException, ParseException {
		Path p = Paths.get(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\RestAssuredAPIAutomation\\src\\test\\resources\\payloads\\EmployeePayloadUsingGson.json");
		System.out.println(p.getFileName());
		System.out.println(p.toAbsolutePath());
		
		byte[] b = Files.readAllBytes(p);
		String str = new String(b);
		//which will decode the byte into the String(that is the Sequence of characters ) uisng the 
		//Constructs a new String by decoding the specified array of bytesusing the platform's default charset. 
		System.out.println(str);
		
		JsonPath path = new JsonPath(str);
		
		String s = path.getString("address");
		System.out.println(s);
		
		FileReader file = new FileReader("C:\\Users\\akilan-18633\\eclipse-workspace\\RestAssuredAPIAutomation\\src\\test\\resources\\payloads\\EmployeePayloadUsingGson.json");
		Object obj = JsonParser.parseReader(file);
		 JSONObject jsonObject = (JSONObject)obj;
		 
		
        String name = (String)jsonObject.get("Name");
        String course = (String)jsonObject.get("Course");
        JSONArray subjects = (JSONArray)jsonObject.get("Subjects");
        System.out.println("The Test Array is " + subjects);
        System.out.println("Name: " + name);
        System.out.println("Name: " + jsonObject.get("Name"));
        System.out.println("Course: " + course);
        System.out.println("Subjects:");
      Iterator<Object> iterator = subjects.iterator();
        while (iterator.hasNext())
        {
                    System.out.println(iterator.next());
                 
              } 
        
	
}}