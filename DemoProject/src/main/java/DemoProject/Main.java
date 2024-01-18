package DemoProject;
import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


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

        String newAddress = "Villaluz park, Tabogo";
        String postResource = "/maps/api/place/add/json";
        String updateResource = "/maps/api/place/update/json";
        String getResource = "/maps/api/place/get/json";

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
                "    \"address\": \""+ newAddress+ "\",\n" +
                "    \"phone_number\": \"(555)33322211\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}")
            .when().put(updateResource)
            .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
            .when().get(getResource).then().assertThat().log().all().statusCode(200)
            .extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");

        System.out.println("Actual Address is: " + actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
    }




}