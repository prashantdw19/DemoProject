import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class CompleJsonParse {

	public static void main(String[] args) 
	{
		
		JsonPath js = new JsonPath(payload.CoursePrice()); //getting json from payload class method CoursePrice. It is a complex json
		                                                   //for which we will try to access different kinds of values
		//Print No of courses returned by API
		
		int count=js.getInt("courses.size()"); //size() method of JsonPath works on arrays only.
		System.out.println("No of courses = "+count);
		
		//Print Purchase Amount
		
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount = "+purchaseAmount);
		
        //Print Title of the first course
		
		String titleFirstCourse=js.getString("courses[0].title");
		System.out.println("Title of the first course = "+titleFirstCourse);
		
		//Print All course titles and their respective Prices
		
		for(int i=0;i<js.getInt("courses.size()");i++)
            {
			//String title=js.getString("courses["+i+"].title");
			//System.out.println(title);
			System.out.println("Course: "+ js.getString("courses["+i+"].title") +", Price: " + js.getInt("courses["+i+"].price"));
            }
		
		//Print no of copies sold by RPA Course
		
		for(int i=0;i<js.getInt("courses.size()");i++)
        {
		String title=js.get("courses["+i+"].title");
		  if(title.equalsIgnoreCase("RPA"))  // REMEMBER, if(title=="RPA")  will not work. Find out why.
		  {
			System.out.println("No of Copies sold for RPA Course: " +js.get("courses["+i+"].copies"));
			break;  //so that no further iteration of loop happens once RPA is found. Optimizes code.
		  }
		
        }
		
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		//run a loop 
		//multiply no of copies into price
		//take sum
		int sum=0;
		for(int i=0;i<js.getInt("courses.size()");i++)
		{
			
		//	sum= sum+ course price * copies
			 sum=sum+(js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies"));
			
		}
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
		
	}

}
