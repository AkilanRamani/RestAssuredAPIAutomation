package APIAutomation;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class APIDefinition {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
		try {
			RestAssured.baseURI = "https://reqres.in/";
			String strURITokenForPost = "api/users";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("name", "John"); // Adding the information as key-value pair in the JSON
			requestParams.put("job", "tester");
			request.body(requestParams);
			request.header("Content-Type", "application/json");
			Response response = request.post(strURITokenForPost); // here we are hitting the uri
			System.out.println("\n Status Code: " + response.getStatusCode()); // Response status code is printed here
			System.out.println("---> Response JSON Body: " + response.getBody().asString());
		} catch (Exception ex) {
			System.out.println("WARNING: " + ex.getMessage() + " In API Util class file.");
		}

		JSONObject data = new JSONObject();

		data.put("employee_name", "MapTest");
		data.put("profile_image", "test.png");
		data.put("employee_age", "30");
		data.put("employee_salary", "11111");

		RestAssured.given().contentType(ContentType.JSON).body(data.toString()).log().all()

				.when().post("https://dummy.restapiexample.com/api/v1/create")

				.then().assertThat().statusCode(200).body("data.employee_name", equalTo("MapTest"))
				.body("data.employee_age", equalTo("30")).body("data.employee_salary", equalTo("11111"))
				.body("data.profile_image", equalTo("test.png"))
				.body("message", equalTo("Successfully! Record has been added.")).log().all();

		// System.out.println(response1);

		JSONObject data1 = new JSONObject();

		data1.put("profile_image", "test.png");

		// Second JSONObject
		JSONObject detail = new JSONObject();

		detail.put("updated_message", "Details of New Resource");
		detail.put("employee_age", "30");

		data.put("employee_details", detail);

		data.put("employee_name", "MapTest");
		data.put("employee_salary", "11111");

		RestAssured.given().contentType(ContentType.JSON).body(data.toString()).log().all()

				.when().post("http://dummy.restapiexample.com/api/v1/create")

				.then().assertThat().statusCode(200).body("data.employee_name", equalTo("MapTest"))
				.body("data.employee_details.employee_age", equalTo("30"))
				.body("data.employee_details.updated_message", equalTo("Details of New Resource"))
				.body("data.employee_salary", equalTo("11111")).body("data.profile_image", equalTo("test.png"))
				.body("message", equalTo("Successfully! Record has been added.")).log().all();

		Map<String, String> map = new HashMap<String, String>();
		map.put("employee_name", "MapTest");
		map.put("employee_salary", "99999");
		map.put("employee_age", "30");
		map.put("profile_image", "test.png");
		RestAssured.given().contentType(ContentType.JSON).body(map).log().all()

				.when().post("https://dummy.restapiexample.com/api/v1/create")

				.then().assertThat().statusCode(200).body("data.employee_name", equalTo("MapTest"))
				.body("data.employee_age", equalTo("30")).body("data.employee_salary", equalTo("99999"))
				.body("message", equalTo("Successfully! Record has been added.")).log().all();
		Map<String, Object> data12 = new HashMap<String, Object>();
		data12.put("employee_name", "MapTest");
		data12.put("profile_image", "test.png");

		// Second JSON Object using Hash Map
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("updated_message", "Details of New Resource");
		msg.put("employee_age", "30");
		data.put("details", msg);
		data.put("employee_salary", "99999");
		RestAssured.given().contentType(ContentType.JSON).body(data).log().all()
				// WHEN
				.when().post("https://dummy.restapiexample.com/api/v1/create")
				// THEN
				.then().assertThat().statusCode(200).body("data.employee_name", equalTo("MapTest"))
				.body("data.details.updated_message", equalTo("Details of New Resource"))
				.body("data.details.employee_age", equalTo("30")).body("data.employee_salary", equalTo("99999"))
				.body("message", equalTo("Successfully! Record has been added.")).log().all();
		

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> inputMap = new HashMap<String, Object>();
 
        inputMap.put("employeeId", "10342256");
        inputMap.put("name", "Vibha Singh");
        inputMap.put("DOB", "12-02-1985");
        inputMap.put("salary", "75000.0");
        inputMap.put("location", "Dublin");
        inputMap.put("contactNumber", "+919999988822");
        inputMap.put("emailId", "abc@test.com");
        inputMap.put("gender", "female");
 
        List<String> skillset = new ArrayList<String>();
 
        skillset.add("Java");
        skillset.add("Teradata");
        skillset.add("Python");
        skillset.add("Power BI");
 
        inputMap.put("skillset", skillset);
 
        // Converting map to a JSON payload as string
        try {
            String employeePrettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputMap);
            System.out.println(employeePrettyJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
 
        String userDir = System.getProperty("user.dir");
 
        //Writing JSON on a file
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(userDir + "\\src\\test\\resources\\JSONFromMap.json"), inputMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestSpecification requestSpecification;
        Response response;
        ValidatableResponse validatableResponse;
     
        String jsonString = "{\"name\":\"newapitest\",\"salary\":\"4000\",\"age\":\"29\"}";
        
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/create";
 
        // Create a request specification
        requestSpecification = RestAssured.given();
 
        // Setting content type to specify format in which request payload will be sent.
        requestSpecification.contentType(ContentType.JSON);
 
        // Adding body as string
        requestSpecification.body(jsonString);
 
        // Calling POST method
        response = requestSpecification.post();
 
        // Let's print response body.
        String responseString = response.prettyPrint();
        System.out.println(responseString);
 
        /*
         * To perform validation on response, we need to get ValidatableResponse type of
         * response
         */
        validatableResponse = response.then();
 
        // Check status code
        validatableResponse.statusCode(200);
 
        // It will check if status line is as expected
        validatableResponse.statusLine("HTTP/1.1 200 OK");
 
        // Check response body - name attribute
        validatableResponse.body("data.name", equalTo("newapitest"));
 
        // Check response body - message attribute
        validatableResponse.body("message", equalTo("Successfully! Record has been added."));
        JSONObject d = new JSONObject();
        
        d.put("employee_name", "ObjectTest");
        d.put("profile_image", "test1.png");
        d.put("employee_age", "30");
        d.put("employee_salary", "11111");
 
        // JSON Object for second employee
        JSONObject data2 = new JSONObject();
 
        data2.put("employee_name", "MapTest");
        data2.put("profile_image", "test2.png");
        data2.put("employee_age", "20");
        data2.put("employee_salary", "99999");
 
        // Creating JSON array to add both JSON objects
        JSONArray array = new JSONArray();
        array.put(d);
        array.put(data2);
 
        // Send the request
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(array.toString())
                .log().all()
 
                .when()
                .post("https://dummy.restapiexample.com/api/v1/create")
 
                .then()
                .assertThat().statusCode(200)
                .body("message", equalTo("Successfully! Record has been added."))
                .log().all();
        JSONObject jsobj  = new JSONObject();
        
        jsobj.put("firstname", "Tom");
        jsobj.put("lastname", "Mathew");
        jsobj.put("age", 59);
        jsobj.put("salary", 720000);
 
        // JSON Object for second employee
        JSONObject js1 = new JSONObject();
 
        js1.put("firstname", "Perry");
        js1.put("lastname", "David");
        js1.put("age", 32);
        js1.put("salary", 365000);
 
        // Creating first JSON array
        JSONArray array1 = new JSONArray();
        array1.put(data1);
 
        // Creating second JSON array
        JSONArray array2 = new JSONArray();
        array2.put(data2);
 
        // Create JSON Object to add both JSONArrays
        JSONObject data3 = new JSONObject();
        data3.put("employee1", array1);
        data3.put("employee2", array2);
 
        System.out.println(data3);
        String endpoint = "https://restful-booker.herokuapp.com/booking/1";
        RestAssured.given().contentType(ContentType.JSON)
        .when().get(endpoint).then()
        .body("totalprice", equalTo(164));

RestAssured.given().contentType(ContentType.JSON)
        .when().get(endpoint)
        .then().body("totalprice",greaterThan(100));

RestAssured.given().contentType(ContentType.JSON)
        .when().get(endpoint)
        .then().body("totalprice",greaterThanOrEqualTo(50));

RestAssured.given().contentType(ContentType.JSON)
        .when().get(endpoint)
        .then().body("totalprice",lessThan(1000));

RestAssured.given().contentType(ContentType.JSON)
        .when().get(endpoint)
        .then().body("totalprice",lessThanOrEqualTo(1000));
RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",equalTo("Mary"));

RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",equalToIgnoringCase("mary"));

RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",containsString("Mary"));

RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",startsWith("M"));

RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",endsWith("y"));

RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("firstname",equalToIgnoringWhiteSpace("   Mary "));
RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("totalprice1", is(nullValue()));
RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("bookingdates",hasKey("checkin"));
RestAssured.given().contentType(ContentType.JSON)
.when().get(endpoint)
.then().body("totalprice",not(equalTo(874)));
File jsonData = new File("src/test/resources/Payloads/jsondemo.json");

// GIVEN
RestAssured.given()
        .baseUri("https://reqres.in")
        .contentType(ContentType.JSON)
        .body(jsonData)

        // WHEN
        .when()
        .post("/api/users")

        // THEN
        .then()
        .assertThat()
        .statusCode(201)
        .body("name", equalTo("Json_Test"))
        .body("job", equalTo("Leader"))
        .log().all();

RestAssured.given()

// When
.when()
.get("https://reqres.in/api/users/2")

// Then
.then()
.statusCode(200).statusLine("HTTP/1.1 200 OK")
.log().all()
.header("Content-Type" , "application/json; charset=utf-8")
.header("Content-Encoding" , "gzip")
.header("Server" , containsString("cloudflare"));

Employee employee = new Employee();
employee.setFirstName("Vibha");
employee.setLastName("Singh");
employee.setAge(30);
employee.setSalary(75000);
employee.setDesignation("Manager");

// Converting a Java class object to a JSON payload as string
ObjectMapper mapper1 = new ObjectMapper();
String employeeJson = mapper1.writeValueAsString(employee);
String employeePrettyJson = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
System.out.println(employeeJson);
System.out.println(employeePrettyJson);

try {
    String employeeJson1 = mapper1.writeValueAsString(employee);
    System.out.println(employeeJson1);
    String employeePrettyJson1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
    System.out.println(employeePrettyJson1);
} catch (JsonProcessingException e) {
    e.printStackTrace();
}   
Employeedetails emp = new Employeedetails();
emp.setName("GsonTest");
emp.setSalary(50000);
emp.setAge(25);

// Converting a Java class object to a JSON payload as string using Gson
Gson builder = new GsonBuilder().setPrettyPrinting().create();
String employeePrettyJsonPayload = builder.toJson(emp);
System.out.println("Request");
System.out.println(employeePrettyJsonPayload);
System.out.println("=========================================");
System.out.println("Response");

// GIVEN
RestAssured.given()
      .baseUri("http://dummy.restapiexample.com/api")
      .contentType(ContentType.JSON).body(emp)

// WHEN
.when()
       .post("/v1/create")

// THEN
.then()
      .assertThat().statusCode(200)
      .body("status", equalTo("success"))
      .body("data.name", equalTo("GsonTest"))
      .body("data.salary", equalTo(50000))
      .body("data.age", equalTo(25))
      .body("message", equalTo("Successfully! Record has been added."))
      .log().body();
String jsonString12 = "{\r\n" + "  \"firstName\": \"Tom\",\r\n" + "  \"lastName\": \"John\",\r\n"
        + "  \"age\": 30,\r\n" + "  \"salary\": 50000.0,\r\n" + "  \"designation\": \"Lead\",\r\n"
        + "  \"contactNumber\": \"+917642218922\",\r\n" + "  \"emailId\": \"abc@test.com\"\r\n" + "}";

Gson gson = new Gson();
// Pass JSON string and the POJO class
Employee employee1 = gson.fromJson(jsonString, Employee.class);

// Now use getter method to retrieve values
System.out.println("Details of Employee is as below:-");
System.out.println("First Name : " + employee1.getFirstName());
System.out.println("Last Name : " + employee1.getLastName());
System.out.println("Age : " + employee1.getAge());
System.out.println("Salary : " + employee1.getSalary());
System.out.println("designation : " + employee1.getDesignation());
System.out.println("contactNumber : " + employee1.getContactNumber());
System.out.println("emailId : " + employee1.getEmailId());
System.out.println("########################################################");
Gson gson1 = new Gson();
// De-serializing from a json file
String userDir1 = System.getProperty("user.dir");
File inputJsonFile = new File(userDir1 + "\\src\\test\\resources\\EmployeePayloadUsingGson.json");
FileReader fileReader = new FileReader(inputJsonFile);
Employee employee12 = gson.fromJson(fileReader, Employee.class);

// Now use getter method to retrieve values
System.out.println("Details of Employee is as below:-");
System.out.println("First Name : " + employee12.getFirstName());
System.out.println("Last Name : " + employee12.getLastName());
System.out.println("Age : " + employee12.getAge());
System.out.println("Salary : " + employee12.getSalary());
System.out.println("designation : " + employee12.getDesignation());
System.out.println("contactNumber : " + employee12.getContactNumber());
System.out.println("emailId : " + employee12.getEmailId());
System.out.println("########################################################");
}





}
