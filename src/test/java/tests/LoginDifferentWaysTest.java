package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;
import pageobject.ForgotPasswordPage;
import pageobject.RegistrationPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

@Epic("Stellar Burgers UI")
@Feature("Различные способы входа")
public class LoginDifferentWaysTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private RegistrationPage registrationPage;
    private User testUser;
    private String accessToken;

    @Before
    @Step("Подготовка тестовых данных")
    public void prepareTestData() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        registrationPage = new RegistrationPage();

        testUser = UserGenerator.getRandomUser();
        var response = UserApi.createUser(testUser);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }
    }

    @Test
    @Story("Вход через кнопку на главной странице")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginViaMainPageButtonTest() {
        openUrl(Constants.BASE_URL);
        mainPage.clickLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Вход через личный кабинет")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalCabinetTest() {
        openUrl(Constants.BASE_URL);
        mainPage.clickPersonalCabinet();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Вход через страницу регистрации")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через страницу регистрации")
    public void loginViaRegistrationPageTest() {
        openUrl(Constants.REGISTER_URL);
        registrationPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.verifyUserLoggedIn();
    }

    @Test
    @Story("Вход через страницу восстановления пароля")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через страницу восстановления пароля")
    public void loginViaPasswordRecoveryTest() {
        openUrl(Constants.FORGOT_PASSWORD_URL);
        forgotPasswordPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.verifyUserLoggedIn();
    }

    @After
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}