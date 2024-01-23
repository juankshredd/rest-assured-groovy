import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test

class Articles extends Base {
    @Test
    void GetArticles_ReturnsList(){
        Response response = get("/articles")

        ArrayList<String> allArticles = response.path("data.title")
        Assert.assertTrue(allArticles.size() > 1, "No articles were returned")
    }

    @Test(groups="Smoke")
    void CreateAndDeleteArticle(){
        File articleFile = new File(getClass().getResource("/article.json").toURI())

        Response createResponse =
                given()
                    .body(articleFile)
                    .when()
                    .post("/article")

        String responseID = createResponse.jsonPath().getString("post.article_id")

        Response deleteResponse =
                given()
                    .body("{\n" +
                            "\t\"article_id\": " + responseID + "\n" +
                            "}")
                    .when()
                    .delete("/articles")
        Assert.assertEquals(deleteResponse.getStatusCode(), 200)
        Assert.assertEquals(deleteResponse.jsonPath().getString("messsage"), "Article successfully deleted")
    }

    @Test
    void DeleteNonExistingArticle_FailMessage(){
        String nonExistingArticleID = "456123"

        Response deleteResponse =
                given()
                    .body("{\n" +
                            "\t\"article_id\": " + nonExistingArticleID + "\n" +
                            "}")
                    .when()
                    .delete("/articles")
        Assert.assertEquals(deleteResponse.getStatusCode(), 500)
        Assert.assertEquals(deleteResponse.jsonPath().getString("error"), "Unable to find article id: " + nonExistingArticleID)
    }

}