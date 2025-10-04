package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

@Epic("Stellar Burgers UI")
@Feature("Вход с главной страницы")
public class LoginFromMainPageTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Вход через кнопку 'Войти в аккаунт' с главной страницы")
    @Owner("Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка логина через главную страницу по кнопке 'Войти в аккаунт'")
    public void loginFromMainPageTest() {
        prepareTestData();

        openUrl(Constants.BASE_URL);
        mainPage.clickLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.verifyUserLoggedIn();
    }

    @Step("Подготовка тестовых данных")
    private void prepareTestData() {
        mainPage = new MainPage();
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