package DemoProject;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args){

        String courseTitle = "Selenium Python";
        JsonPath js = new JsonPath(Payload.coursePrice());
        // Print number of courses retorned by api
        int count = js.getInt("courses.size()");
        System.out.println("The amount of courses raised to: " + count);
        // print purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Total amount is: " + totalAmount);
        // print title of the first course
        String courseName = js.getString("courses[1].title");
        System.out.println("Course name is: " + courseName);
        // print all courses titles and respective prices
        for(int i = 0; i < count; i++){
            String titles = js.get("courses["+i+"].title");
            int coursesPrice = js.getInt("courses["+i+"].price");
            System.out.println("Course title is: " + titles + " and it´s price is: US$ " + coursesPrice);
        }
        //  number of cpies sold by RPA
        for(int i = 0; i < count; i++){
            String titles = js.get("courses["+i+"].title");
                if(titles.equalsIgnoreCase(courseTitle)){
                    // copies sold
                    int copiesSold = js.get("courses["+i+"].copies");
                    System.out.println("Copies sold of course " + titles +": " + copiesSold);
                    break;
                };
        }

        //
    }
}
