package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Test;
import pageobject.RegistrationPage;
import pageobject.LoginPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers UI")
@Feature("Регистрация пользователя")
public class RegistrationTest extends BaseTest {
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Успешная регистрация")
    @Owner("Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();
        testUser = UserGenerator.getRandomUser();

        openUrl(Constants.REGISTER_URL);
        registrationPage.registerUser(testUser.getName(), testUser.getEmail(), testUser.getPassword());
        registrationPage.verifyRegistrationSuccess();

        // Получаем токен для удаления пользователя
        var response = UserApi.loginUser(testUser);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }
    }

    @Test
    @Story("Регистрация с коротким паролем")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка регистрации с паролем короче 6 символов")
    public void registrationWithShortPasswordTest() {
        registrationPage = new RegistrationPage();
        testUser = UserGenerator.getRandomUser();

        openUrl(Constants.REGISTER_URL);
        registrationPage.registerUser(testUser.getName(), testUser.getEmail(), "123");
        registrationPage.verifyPasswordErrorDisplayed();
        registrationPage.verifyPasswordErrorText("Некорректный пароль");
    }

    @Test
    @Story("Регистрация с невалидным email")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка регистрации с некорректным email")
    public void registrationWithInvalidEmailTest() {
        registrationPage = new RegistrationPage();

        openUrl(Constants.REGISTER_URL);
        registrationPage.registerUser("Test User", "invalid-email", "password123");

        // Проверяем, что остались на странице регистрации (не было редиректа)
        registrationPage.verifyRegistrationPageOpened();
    }

    @After
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }

    }
}