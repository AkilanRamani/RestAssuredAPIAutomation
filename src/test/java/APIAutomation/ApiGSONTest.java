package APIAutomation;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiGSONTest {

	public static void main(String[] args) {

		Employee employee = new Employee();
		employee.setFirstName("Vibha");
		employee.setLastName("Singh");
		employee.setAge(30);
		employee.setSalary(75000);
		employee.setDesignation("Manager");
		employee.setContactNumber("+919999988822");
		employee.setEmailId("abc@test.com");

		Gson builder = new GsonBuilder().setPrettyPrinting().create();
		String employeePrettyJsonPayload = builder.toJson(employee);
		System.out.println(employeePrettyJsonPayload);

		String userDir = System.getProperty("user.dir");
		File outputJsonFile = new File(userDir + "\\src\\test\\resources\\payloads\\jsondemo.json");
		try {
			FileWriter fileWriter = new FileWriter(outputJsonFile);
			builder.toJson(employee, fileWriter);
			// This method serializes the specified object into its equivalent JSON
			// representation andwrites it to the writer.
			fileWriter.flush();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
