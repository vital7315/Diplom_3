package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegistrationPage;
import api.UserApi;
import utils.UserCleaner;

import static com.codeborne.selenide.Selenide.*;

@Epic("Stellar Burgers UI")
@Feature("User Registration")
public class RegistrationTest {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_URL = BASE_URL + "/register";

    private RegistrationPage registrationPage;
    private String accessToken;
    private String testEmail;
    private String testPassword = "password123";
    private String testName = "Test User";

    @Before
    public void setUp() {
        setupBrowser();
        open(REGISTER_URL);
        registrationPage = new RegistrationPage();

        // Генерация уникального email для каждого теста
        testEmail = "testuser" + System.currentTimeMillis() + "@yandex.ru";
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserCleaner.deleteUser(accessToken);
        }
        closeWebDriver();
    }

    private void setupBrowser() {
        // Настройка браузера из системных переменных или свойств
        String browser = System.getProperty("browser", "chrome");
        Configuration.browser = browser;
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout = 10000;
    }

    @Test
    @Story("Successful registration")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        // Заполняем форму регистрации
        registrationPage.setName(testName);
        registrationPage.setEmail(testEmail);
        registrationPage.setPassword(testPassword);
        registrationPage.submitRegistration();

        // Проверяем успешность регистрации
        registrationPage.verifyRegistrationSuccess();

        // Получаем токен для удаления пользователя
        try {
            var response = UserApi.loginUser(testEmail, testPassword);
            accessToken = response.jsonPath().getString("accessToken");
        } catch (Exception e) {
            System.out.println("Не удалось получить токен: " + e.getMessage());
        }
    }

    @Test
    @Story("Registration with short password")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка регистрации с паролем короче 6 символов")
    public void registrationWithShortPasswordTest() {
        // Заполняем форму с коротким паролем
        registrationPage.setName(testName);
        registrationPage.setEmail(testEmail);
        registrationPage.setPassword("123");
        registrationPage.submitRegistration();

        // Проверяем отображение ошибки
        registrationPage.verifyPasswordErrorDisplayed();

        // Также можно проверить текст ошибки
        String errorText = registrationPage.getPasswordErrorText();
        System.out.println("Текст ошибки: " + errorText);
    }

    @Test
    @Story("Registration with invalid email")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка регистрации с некорректным email")
    public void registrationWithInvalidEmailTest() {
        registrationPage.setName(testName);
        registrationPage.setEmail("invalid-email");
        registrationPage.setPassword(testPassword);
        registrationPage.submitRegistration();

        // Проверяем отображение ошибки email
        registrationPage.emailError.shouldBe(com.codeborne.selenide.Condition.visible);
    }
}