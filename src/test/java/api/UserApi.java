package api;

import io.restassured.response.Response;
import model.User;
import static io.restassured.RestAssured.given;
import utils.Constants;

public class UserApi {

    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(Constants.BASE_URL + Constants.API_REGISTER);
    }

    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(Constants.BASE_URL + Constants.API_LOGIN);
    }

    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(Constants.BASE_URL + Constants.API_USER);
    }
}