import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;


public class SumValidation 

{
   @Test
   public void sumOfCourses()
   {
	    JsonPath js= new JsonPath(payload.CoursePrice());
	    int sum=0;
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
			
		//	sum= sum+ course price * copies
			 sum=sum+(js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies"));
			
		}
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
	   
   }
	
	
}
