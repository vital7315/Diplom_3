package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegistrationPage;
import utils.UserCleaner;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class RegistrationTest {
    private String accessToken;
    private String testEmail;
    private String testPassword;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @Description("Позитивный кейс: регистрация с валидными данными")
    public void registrationWithValidData() {
        RegistrationPage regPage = new RegistrationPage();
        testEmail = "testuser" + System.currentTimeMillis() + "@mail.ru";
        testPassword = "Qwerty321";
        regPage.setName("TestUser123");
        regPage.setEmail(testEmail);
        regPage.setPassword(testPassword);
        regPage.submitRegistration();

        // Проверка перехода на страницу входа
        $("h2").shouldHave(text("Вход"));

        // Получаем accessToken через API
        String json = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", testEmail, testPassword);
        accessToken = RestAssured.given()
                .header("Content-type", "application/json")
                .body(json)
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .extract()
                .path("accessToken");
    }

    @Test
    @Description("Негативный кейс: попытка регистрации с коротким паролем")
    public void registrationWithShortPassword() {
        RegistrationPage regPage = new RegistrationPage();
        regPage.setName("ShortPass");
        regPage.setEmail("bad" + System.currentTimeMillis() + "@mail.ru");
        regPage.setPassword("123"); // короткий пароль
        regPage.submitRegistration();

        // Проверяем, что появилась ошибка по паролю
        regPage.passwordError.shouldHave(text("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        // Удаляем пользователя, если зарегистрировались
        if (accessToken != null) {
            UserCleaner.deleteUser(accessToken);
        }
    }
}
