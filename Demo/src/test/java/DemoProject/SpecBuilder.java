package DemoProject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder {

    public static void main(String[] args){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Creating a POJO class object
        AddPlace p = new AddPlace();
        // Setting object's properties
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+57)3235588");
        p.setName("Frontline house");
        p.setWebsite("https://rahulshettyacademy.com");
        // Creating a List object that expect String attributes
        List<String> myList = new ArrayList<String>();
        //Adding components to the list
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        // Creating a POJO object for setting type attribute
        Location l = new Location();
        // setting lat and lng creation of brief json
        l.setLat(-38.383494);
        l.setLng(33.427362);
        //setting location object values to the object addPlace
        p.setLocation(l);

        //Spec builder

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
            .setContentType(ContentType.JSON).build();

        ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        // the complete request object
        RequestSpecification res = given().spec(req).body(p);
        // using spec builder
        Response response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);
    }
}
