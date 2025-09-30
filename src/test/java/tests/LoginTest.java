package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Test;
import pageobject.LoginPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

@Epic("Stellar Burgers UI")
@Feature("Авторизация")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Вход с валидными учетными данными")
    @Owner("Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Вход с корректными логином и паролем")
    public void loginWithValidCredentialsTest() {
        prepareTestData();

        openUrl(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        loginPage.verifyLoginPageOpened(); // Должен произойти редирект
    }

    @Test
    @Story("Вход с невалидными учетными данными")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка входа с некорректным паролем — должна появиться ошибка")
    public void loginWithInvalidPasswordTest() {
        loginPage = new LoginPage();

        openUrl(Constants.LOGIN_URL);
        loginPage.login("invalid@yandex.ru", "wrongpassword");
        loginPage.verifyPasswordErrorDisplayed();
        loginPage.verifyPasswordErrorText("Некорректный пароль");
    }

    @Step("Подготовка тестовых данных")
    private void prepareTestData() {
        loginPage = new LoginPage();

        testUser = UserGenerator.getRandomUser();
        var response = UserApi.createUser(testUser);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }
    }

    @After
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}