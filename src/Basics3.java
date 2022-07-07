import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //For equalTO method

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
public class Basics3 {

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
	    String newAddress= "Naya Pata, Naya Sheher";
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\n"
	    		+ "\"place_id\":\""+placeId+"\",\n"   // inside strings, variables are placed within + operator inside double quotes
	    		+ "\"address\":\""+newAddress+"\",\n"  // var is added inside string using "+varName+"
	    		+ "\"key\":\"qaclick123\"\n"
	    		+ "}\n"
	    		+ "").
	    		when().put("maps/api/place/update/json")
	    		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	   
	    
	    System.out.println("#########################################################################################");
	    
	    // NOW CHECKING USING GETPLACE whether address has been updated to Naya Pata, Naya Sheher
	   
	    String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId).
	    when().get("maps/api/place/get/json").
	    then().assertThat().log().all().statusCode(200).extract().response().asString();
	    
	    //now create a custom method to parse response to json and get desired value for given key
	    JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse); // using a method we created in files to make code readable
	    String actualAddress=js1.getString("address");
	    System.out.println("Actual address is : "+actualAddress);
	    //Now since we did not assert the address that is coming from getPlace in the given when then system, we will have to use
	    //testing library like Cucumber, JUnit or TestNg. So we will add TestNG dependency to POM. But Rahul Shetty just downloads testng7.6.0.jar 
	    //and adds it to java build path under libraries using add external jars option.
	    
	    //Using Asssert from TestNg
	    Assert.assertEquals(actualAddress, newAddress);
	
	}

}