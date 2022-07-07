import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //For equalTO method

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class StaticJsonFromFile {

	public static void main(String[] args) throws IOException 
	{
		// To use a json file, you have to convert it to string but that does not happen directly.
		// First read the file as a byte file
		// Then the byte file is converted to String
        
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("/Users/pdwivedi/eclipse-workspace/DemoProject/src/files/addPlace.json")))).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)")
		;        
		
		//use .log().all() after every method of given when and then so that their details are logged.
		//Add place -  Update Place - Get Place to validate if address is present in response.
        // Update Place will need 	
		
	
		
		
	}

}
