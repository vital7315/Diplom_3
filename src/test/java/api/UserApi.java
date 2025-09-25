package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response createUser(String email, String password, String name) {
        User user = new User(email, password, name);

        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    public static Response loginUser(String email, String password) {
        UserCredentials credentials = new UserCredentials(email, password);

        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .post("/api/auth/login");
    }

    // Вспомогательные классы для сериализации в JSON
    public static class User {
        private String email;
        private String password;
        private String name;

        public User(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        // Getters для сериализации
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getName() { return name; }
    }

    public static class UserCredentials {
        private String email;
        private String password;

        public UserCredentials(String email, String password) {
            this.email = email;
            this.password = password;
        }

        // Getters для сериализации
        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }
}