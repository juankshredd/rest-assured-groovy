package DemoProject;

import static io.restassured.RestAssured.given;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

public class JiraTest {
    public static void main(String[] args){
        // USING COOKIE
        // Login and response capture
        RestAssured.baseURI = "http://localhost:8080";
        String key = "10003";
        SessionFilter session = new SessionFilter();
        // create a variable to set the value of captured response
        String response = given().header("Content-Type", "application/json").body("{\n" +
            "    \"username\": \"juanK\",\n" +
            "    \"password\": \"159Wsad357*\"\n" +
            "}").log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
        // jsonpath instance to find values from response
        JsonPath js = ReUsableMethods.rawToJson(response);
        String cookie = js.get("session.value");
        String name = js.get("session.name");
        System.out.println("session name is: " + name + "\nsession cookie is : " + cookie);

        String expectedMessage = "Booyah!! this is another automated comment using rest assured, bro!.";

        // adding a comment to an issue
        String addCommentResponse = given().pathParam("key", "10003").log().all().header("Content-Type", "application/json").header("Cookie", name+"="+cookie).body("{\n" +
            "    \"body\": \"" + expectedMessage + "\",\n" +
            "    \"visibility\": {\n" +
            "        \"type\": \"role\",\n" +
            "        \"value\": \"Administrators\"\n" +
            "    }\n" +
            "}").filter(session).when().post("/rest/api/2/issue/{key}/comment")
            .then().log().all().assertThat().statusCode(201).extract().response().toString();
        System.out.println("addCommentResponse : " + addCommentResponse);

        // Add attachment
        given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", key)
            .header("Content-Type", "multipart/form-data")
            .multiPart("file", new File("jira.txt")).when().post("/rest/api/2/issue/{key}/attachments")
            .then().log().all().assertThat().statusCode(200);

        // get an issue

        String issueDetails = given().filter(session).pathParam("key", key)
            .queryParam("fields", "comment").log().all()
            .when().get("/rest/api/2/issue/{key}")
            .then().log().all().extract().response().asString();
        System.out.println(issueDetails);

        JsonPath js1 = new JsonPath(issueDetails);
        int commentsCount = js1.getInt("fields.comment.comments.size()");
        String commentId;
        for(int i = 0; i < commentsCount; i++){
            commentId = js1.get("fields.comment.comments["+i+"].id");
            if(commentId.equalsIgnoreCase(commentId)){
            String message = js1.get("fields.comment.comments["+i+"].body").toString();
                System.out.println("actual message: " + message + "\nexpected message: "+ expectedMessage);
                Assert.assertEquals(message, expectedMessage);
            }
        }

    }
}
