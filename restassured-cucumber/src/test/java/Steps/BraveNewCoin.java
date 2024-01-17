package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.File;
import java.util.List;
import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BraveNewCoin {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I have a valid API key for the (.+) URI")
    public void iSetTheRequestParams(String URI) {
        request = given().relaxedHTTPSValidation().header("x-rapidapi-key", "76f509e6f1msh987ac2c5eb1b70fp1bb995jsnfe856cc6572a")
            .header("x-rapidapi-host", "bravenewcoin.p.rapiapi.com")
            .contentType(ContentType.JSON)
            .baseUri(URI)
            .log().all();
    }

    @When("^I send a POST request with a valid (.+) payload to the (.+) endpoint$")
    public void sendPostRequest(String endPoint, String payload) {
        File requestBody = new File("resources/payloads/" + payload +".json");
        response = request.when().body(requestBody).post(endPoint).prettyPeek();
    }

    @Then("^I can validate I receive a valid token in the response$")
    public void validateTheToken() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("^I have an invalid API key for the (.+) URI")
    public void iSetTheRequestParams1(String URI) {
        request = given().header("x-rapidapi-key", "76f509e6f1msh987ac2c5eb1b70fp1bb995jsnfe856cc6572a")
            .header("x-rapidapi-host", "bravenewcoin.p.rapiapi.com")
            .contentType(ContentType.JSON)
            .baseUri(URI)
            .log().all();
    }

    @Then("^I can validate I receive a HTTP Status (\\d+)$")
    public void validateTheToken1() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
