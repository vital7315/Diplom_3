package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response createUser(String email, String password, String name) {
        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                email, password, name);

        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .post("/api/auth/register");
    }

    public static Response loginUser(String email, String password) {
        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .post("/api/auth/login");
    }
}