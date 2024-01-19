package DemoProject;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class DynamicJson {
    @Test
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json")
            .body(Payload.addBook("227", "bcd"))
            .when().post("/Library/Addbook.php")
            .then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("The id is: " + id);
    }
}
