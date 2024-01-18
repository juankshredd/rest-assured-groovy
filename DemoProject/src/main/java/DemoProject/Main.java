package DemoProject;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class Main {
    public static void main(String[] args) {
        // validate if add api is working as expected
        // principles of est assured
        // given - all input details
        // when - submit the api - resource http methods
        // then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postResource = "/maps/api/place/add/json";
        String updateResource = "/maps/api/place/update/json";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
            .body(Payload.AddPlace()).when().post(postResource)
            .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
            .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
        System.out.println("***************************\n" +response + "\n***************************");
        JsonPath js = new JsonPath(response); // for parsing json
        String placeId = js.getString("place_id");

        System.out.println("==== PlaceId ====\n" + placeId + "\n=================");

        // update place
        given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
            .body("{\n" +
                "    \"place_id\": \""+placeId+"\",\n" +
                "    \"address\": \"77 Villaluz park, Tabogo\",\n" +
                "    \"phone_number\": \"(555)33322211\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}")
            .when().put(updateResource)
            .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
    }




}