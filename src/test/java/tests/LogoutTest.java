package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import pageobject.MainPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

@Epic("Stellar Burgers UI")
@Feature("Выход из системы")
public class LogoutTest extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private MainPage mainPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Выход из профиля")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Выход из профиля пользователя")
    public void logoutFromProfileTest() {
        prepareTestData();

        openUrl(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.clickPersonalCabinet();
        profilePage.verifyProfilePageOpened();
        profilePage.clickLogoutButton();
        loginPage.verifyLoginPageOpened();
    }

    @Step("Подготовка тестовых данных")
    private void prepareTestData() {
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        mainPage = new MainPage();

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