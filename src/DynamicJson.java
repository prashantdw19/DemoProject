import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //MANUAL IMPORT TO BE TYPED IN FOR GIVEN WHEN THEN METHODS AS ECLIPSE WON'T SUGGEST

import  io.restassured.path.json.JsonPath;

public class DynamicJson 
{
  @Test (dataProvider= "BooksData")
  public void addBook(String aisle, String isbn)
  {
	  RestAssured.baseURI="http://216.10.245.166";
	  String response =  given().log().all().header("Content-Type", "application/json").body(payload.AddBook(aisle, isbn)).
			             when().post("/Library/Addbook.php").
			             then().log().all().assertThat().statusCode(200).extract().response().asString();
 
    	//Now we are getting the response of our API in String. This has to be converted to json so that JsonPath js methods can be
	  //used to access desired properties from the json
	  
  
	  JsonPath js = ReusableMethods.rawToJson(response);  //rawToJson is a method we wrote in a class we created in files package for the epynonymous purpose.
               String id = js.get("ID");  //js.get("key") returns string by default
               System.out.println("ID is: " +id);
  }
	
   @DataProvider(name= "BooksData")
   public Object[][] getData()
   {
	   return new Object [][] {{"acava", "6587"}, {"cdjer", "5587"}, {"adava", "6584"}, {"hcava", "6554"}, {"acavv", "6787"}};
   }
	
	
	
	
	
	
	
	
}
