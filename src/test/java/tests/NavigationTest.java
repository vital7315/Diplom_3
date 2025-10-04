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
@Feature("Навигация")
public class NavigationTest extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private MainPage mainPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Переход из профиля в конструктор через кнопку")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    public void navigateFromProfileToConstructorViaButtonTest() {
        prepareTestData();

        openUrl(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.clickPersonalCabinet();
        profilePage.verifyProfilePageOpened();
        profilePage.clickConstructorButton();
        mainPage.verifyMainPageOpened();
    }

    @Test
    @Story("Переход из профиля в конструктор через логотип")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Переход из личного кабинета в конструктор по логотипу")
    public void navigateFromProfileToConstructorViaLogoTest() {
        prepareTestData();

        openUrl(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.clickPersonalCabinet();
        profilePage.verifyProfilePageOpened();
        profilePage.clickLogo();
        mainPage.verifyMainPageOpened();
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