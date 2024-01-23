package DemoProject;
import static io.restassured.RestAssured.*;
public class OAUthTest {

    public static void main(String[] args){
        String response = given().formParams("cliente_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
            .formParams("grant_type", "client_credentials")
            .formParams("scope", "trust")
            .when().log().all()
            .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
    }
}