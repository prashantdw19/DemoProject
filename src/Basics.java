import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //For equalTO method
public class Basics {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        //given- all input details - resource and http method used
		//when- submit the api
		//then- validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -37.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Nayijagah\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}\n"
				+ "").
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)")
		;        
		
		//use .log().all() after every method of given when and then so that their details are logged.
		//Add place -  Update Place - Get Place to validate if address is present in response.
        // Update Place will need 	
		
	
		
		
	}

}
