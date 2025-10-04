package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserCleaner {
    public static void deleteUser(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) return;
        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then()
                .statusCode(202); // Обычно сервер отдаёт 202 на успешное удаление пользователя
    }
}

