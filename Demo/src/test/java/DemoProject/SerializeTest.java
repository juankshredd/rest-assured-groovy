package DemoProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {

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



        Response res = given().log().all().queryParam("key", "qaclick123").body(p)
            .when().post("/maps/api/place/add/json")
            .then().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
    }
}
