package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;
import api.UserApi;
import utils.UserCleaner;

import static com.codeborne.selenide.Selenide.*;

@Epic("Stellar Burgers UI")
@Feature("Login Different Ways")
public class LoginDifferentWaysTest {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String EMAIL = "testuser" + System.currentTimeMillis() + "@yandex.ru";
    private static final String PASSWORD = "password123";
    private static final String NAME = "Test User";

    private MainPage mainPage;
    private LoginPage loginPage;
    private String accessToken;

    @Before
    public void setUp() {
        setupBrowser();

        // Создание пользователя через API перед тестом
        var response = UserApi.createUser(EMAIL, PASSWORD, NAME);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        mainPage = new MainPage();
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        // Удаление пользователя после теста
        if (accessToken != null) {
            UserCleaner.deleteUser(accessToken);
        }
    }

    private void setupBrowser() {
        // Настройка браузера из системных переменных или свойств
        String browser = System.getProperty("browser", "chrome");
        Configuration.browser = browser;
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    @Test
    @Story("Login via main page button")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginViaMainPageButtonTest() {
        open(BASE_URL);
        mainPage.clickLoginButton();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Login via personal cabinet")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalCabinetTest() {
        open(BASE_URL);
        mainPage.clickPersonalCabinet();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Login via registration page")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через страницу регистрации")
    public void loginViaRegistrationPageTest() {
        open(BASE_URL + "/register");
        loginPage.clickLoginLink();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Login via password recovery page")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через страницу восстановления пароля")
    public void loginViaPasswordRecoveryTest() {
        open(BASE_URL + "/forgot-password");
        loginPage.clickLoginLink();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.verifyUserLoggedIn();
    }
}