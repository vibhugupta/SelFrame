package WebelementHelper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by vibhu on 3/12/2018.
 */
public class Apireading {

    public void apptesting() {
/*        given().get("https://api.themoviedb.org/3/discover/movie?api_key=ffc2340300f9c23d6de9af3b45c70c50&language=en-US&with_genres=36").then()
                .statusCode(200).log().all();*/
        Response response = given().get("https://api.themoviedb.org/3/discover/movie?api_key=ffc2340300f9c23d6de9af3b45c70c50&language=en-US&with_genres=36")
                .then().contentType(ContentType.JSON).extract().response();

        List<Map<String,?>> resultString = response.jsonPath().getList("results");

        for(Map<String,?> cardsItem : resultString) {
            if(cardsItem.get("id").toString().equalsIgnoreCase("374720")) {
                System.out.println(cardsItem.get("title"));
                break;
            }
        }

    }
}
