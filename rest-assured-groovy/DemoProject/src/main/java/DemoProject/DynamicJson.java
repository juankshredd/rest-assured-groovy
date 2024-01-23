package DemoProject;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;


import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {
    @Test(dataProvider = "booksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        // using Payload class to dynamically load info
        String response = given().header("Content-Type", "application/json")
            .body(Payload.addBook(isbn, aisle))
            .when().post("/Library/Addbook.php")
            .then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("The id is: " + id);
    }

    @DataProvider(name="booksData")
    public Object[][] getData(){
        return new Object[][] {{"oghvt", "8981"},{"uiodsh", "5956"},{"mbbsa", "0203"}, {"holi", "0123"}};
    }
}
