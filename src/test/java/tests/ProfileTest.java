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
@Feature("Профиль пользователя")
public class ProfileTest extends BaseTest {
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private MainPage mainPage;
    private User testUser;
    private String accessToken;

    @Test
    @Story("Переход в личный кабинет")
    @Owner("Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода в ЛК и наличия кнопки 'Выйти'")
    public void goToPersonalCabinetTest() {
        prepareTestData();

        openUrl(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.clickPersonalCabinet();
        profilePage.verifyProfilePageOpened();
        profilePage.verifyProfileLinkActive();
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