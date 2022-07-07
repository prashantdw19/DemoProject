import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //For equalTO method

import files.payload;
public class Basics2 {

	public static void main(String[] args) 
	{
	
        //given- all input details - resource and http method used
		//when- submit the api
		//then- validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payload.AddPlace()).
		when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().asString()
		;        
		
		//use .log().all() after every method of given when and then so that their details are logged.
		System.out.println(response);
		
		JsonPath js= new JsonPath(response); //Parses String response to json format;
		String placeId= js.getString("place_id");
	    System.out.println("Place id is: "+placeId);
	    System.out.println("#########################################################################################");
	    
	    
	    
	    // NOW UPDATING PLACE USING putPlace
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\n"
	    		+ "\"place_id\":\""+placeId+"\",\n"   // inside strings, variables are placed within + operator inside double quotes
	    		+ "\"address\":\"Naya Pata, Naya Sheher\",\n"
	    		+ "\"key\":\"qaclick123\"\n"
	    		+ "}\n"
	    		+ "").
	    		when().put("maps/api/place/update/json")
	    		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	   
	    
	    System.out.println("#########################################################################################");
	    
	    // NOW CHECKING USING GETPLACE whether address has been updated to Naya Pata, Naya Sheher
	   
	    given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId).
	    when().get("maps/api/place/get/json").
	    then().assertThat().statusCode(200).body("address", equalTo("Naya Pata, Naya Sheher"));
	    
	
	}

}
