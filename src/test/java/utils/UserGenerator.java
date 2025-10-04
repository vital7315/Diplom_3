package utils;

import model.User;

public class UserGenerator {
    public static User getRandomUser() {
        String email = "test" + System.currentTimeMillis() + "@yandex.ru";
        String password = "password123";
        String name = "TestUser";
        return new User(email, password, name);
    }

    public static User getUserWithoutEmail() {
        return new User(null, "password123", "TestUser");
    }
}