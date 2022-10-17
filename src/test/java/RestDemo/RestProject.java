package RestDemo;

import java.util.Scanner;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestProject {

	@Test
	public void searchCapitalofCountries() {
		// base url
		RestAssured.baseURI = "https://restcountries.com/v3.1/name/";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		while (true) {
			Scanner myObj = new Scanner(System.in); // Create object of scanner
			System.out.println("Enter country name to see the capital city"); // request user input
			String coutryName = myObj.nextLine().toLowerCase(); // Read user input both capital or lowercase
			Response response = null;
			try {
				response = httpRequest.request(Method.GET, coutryName); // call get method by country name

				
				int statusCode = response.getStatusCode();
				if (statusCode != 200) {
					System.out.println("Country name doesn't match in our system!"); // Negative Test Case
					break;
				}
			} catch (Exception e) {
			}

			// String manipulation
			String responseBody = response.getBody().asString();
			int index = responseBody.indexOf("\"capital\":[\"");
			int indexEnd = responseBody.indexOf("\"],\"altSpellings\"");
			String capital = responseBody.substring(index, indexEnd);
			String[] capitalCity = capital.split(" ] ");
			System.out.println("The capital city of " + coutryName + " is: " + capitalCity[0].substring(12));
			break;

		}
	}

}