package DemoProject;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.*;
public class OAUthTest {

    public static void main(String[] args){

        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
        String requestedCourse = "SoapUI Webservices testing";

        String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
            .formParams("grant_type", "client_credentials")
            .formParams("scope", "trust")
            .when().log().all()
            .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println("Response 1---> " + response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");


        GetCourse cg = given().queryParams("access_token", accessToken)
            .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails\n").as(GetCourse.class);
        System.out.println("Response 2---> " + cg.getCourses());

        System.out.println(cg.getLinkedIn());
        System.out.println(cg.getInstructor());

        // solving complex queries from json with simple POJO methods
        System.out.println("The requested course is: " + cg.getCourses().getApi().get(1).getCourseTitle());
        // Iterating over courses and extract info based on the name of the course
        List<Api> apiCourses = cg.getCourses().getApi();
        for(int i = 0; i < apiCourses.size(); i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase(requestedCourse)){
                System.out.println("The price for "+ requestedCourse + " is: $ " + apiCourses.get(i).getPrice());
            }
        }

        // Get the course names of webAutomation
        ArrayList<String> a = new ArrayList<String>();

        List<WebAutomation> w = cg.getCourses().getWebAutomation();
        for(int i=0;i<w.size();i++){
            //System.out.println("The course ---> " + w.get(i).getCourseTitle() + " costs: $ " + w.get(i).getPrice());
            a.add(w.get(i).getCourseTitle());
        }
        List<String> expectedList = Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));

        String response2 = given().queryParams("access_token", accessToken)
            .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails\n").asString();
        System.out.println("Response 2---> " + response2);

    }
}