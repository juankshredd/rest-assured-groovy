package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class APISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Test
    @Given("^I send a GET request to the (.+) URI$")
    public void sendGETRequest(String URI){
        request = given()
                    .baseUri(URI)
                    .contentType(ContentType.JSON);
    }
    @Test
    @Then("^I get a (\\d+) status code$")
    public void expectedStatusCode(int statusCode){
        response = request
                    .when()
                    .get("/users/TheFreeRangeTester/repos");
        json = response.then().statusCode(statusCode);

    }
    @Test
    @Then("^I validate there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int expectedSize, String endPoint){
        response = request
                    .when()
                    .get(endPoint);

        List<String> jsonResponse = response.jsonPath().getList("$");
        int actualSize = jsonResponse.size();

        assertEquals(expectedSize, actualSize);
    }
    @Test
    @Then("^I validate there is a value: (.+) in the (.+) endpoint$")
    public void validateValue(String expectedValue, String endPoint){
        response = request
                    .when()
                    .get(endPoint);

        List<String> jsonResponse = response.jsonPath().getList("username");
//        String actualValue = jsonResponse.get(0);
//        assertEquals(expectedValue,actualValue);
        assertTrue("El valor " + expectedValue + " no se encuentra en la lista", jsonResponse.contains(expectedValue));

    }

    @Test
    @Then("^I can validate the nested value: (.+) in the response at (.+) endpoint$")
    public void validateNestedValue(String expectedStreet, String endPoint){
        response = request
            .when()
            .get(endPoint);

        String jsonResponse = response.jsonPath().getString("address.street");
        assertTrue("La calle " + expectedStreet + " no pertenece a ningun usuario", jsonResponse.contains(expectedStreet));

    }


    @Test
    @Then("^I can validate the nested value: (.+) in the response at (.+) endpoint2$")
    public void validateNestedValue2(String expectedStreet, String endPoint){
        response = request
            .when()
            .get(endPoint);

        String jsonResponse = response.jsonPath().getString("address.geo.lat");
        assertTrue("La latitud " + expectedStreet + " no ubica a ningun usuario", jsonResponse.contains(expectedStreet));

    }

    

}
