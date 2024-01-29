package DemoProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public static void main(String[] args) {
        String baseUrl = "https://rahulshettyacademy.com";
        // se crea el objeto spec builders
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).build();
        // se crea el objeto de la clase POJO
        LoginRequest loginRequest = new LoginRequest();
        // se setean los valores del request
        loginRequest.setUserEmail("juan.bohorquez.perficient@gmail.com");
        loginRequest.setUserPassword("tgh22Hw4wjjeG.X");

                                            // SSL bypass
        RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
        // se crea objeto de POJO class para manejar la respuesta y extraerla
        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
            .response().as(LoginResponse.class);
        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();
        System.out.println("Token--> : " + token);
        System.out.println("UserId--> : " + userId);

        // Add product
        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                                                        .setBaseUri(baseUrl).addHeader("authorization", token)
                                                        .build();

        RequestSpecification reqAddProd =  given().log().all().spec(addProductBaseReq).
            param("productName", "action comics")
            .param("productAddedBy", userId).param("productCategory", "books")
            .param("productSubCategory", "Comics").param("productPrice", 8900)
            .param("productDescription", "superman comic").param("productFor", "all")
            .multiPart("productImage", new File("C:\\Users\\juan.bohorquezm\\Downloads\\ACTIONCOMICS_Cv1033.jpg"));
        String addProdResponse = reqAddProd.when().post("/api/ecom/product/add-product")
            .then().log().all().extract()
            .response().asString();
        JsonPath js = new JsonPath(addProdResponse);
        String productId = js.get("productId");

        // create order
        RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri(baseUrl)
            .addHeader("authorization", token).setContentType(ContentType.JSON)
            .build();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("USA");
        orderDetails.setProductOrderedId(productId);

        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        orderDetailsList.add(orderDetails);

        Orders orders = new Orders();
        orders.setOrders(orderDetailsList);

        RequestSpecification createOrderReq =  given().log().all().spec(createOrderBaseReq).body(orders);

        String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
            .then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);

        // delete product
        RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri(baseUrl)
            .addHeader("authorization", token).setContentType(ContentType.JSON)
            .build();

        RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBaseReq).pathParam("productId", productId);
        String deleteProdResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
            .then().log().all().extract().response().asString();
        JsonPath js1 = new JsonPath(deleteProdResponse);
        Assert.assertEquals("Product Deleted Successfully", js1.get("message"));

    }
}
