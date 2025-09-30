package tests;

import io.qameta.allure.*;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.ConstructorPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import utils.UserGenerator;
import api.UserApi;
import utils.Constants;

import static com.codeborne.selenide.Selenide.open;

@Epic("Stellar Burgers UI")
@Feature("Конструктор бургеров")
public class ConstructorTest extends BaseTest {
    private ConstructorPage constructorPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private User testUser;
    private String accessToken;

    @Before
    @Step("Подготовка тестовых данных")
    public void prepareTestData() {
        constructorPage = new ConstructorPage();
        mainPage = new MainPage();
        loginPage = new LoginPage();

        // Создание тестового пользователя
        testUser = UserGenerator.getRandomUser();
        var response = UserApi.createUser(testUser);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }

        open(Constants.LOGIN_URL);
        loginPage.login(testUser.getEmail(), testUser.getPassword());
    }

    @Test
    @Story("Навигация к разделу 'Булки")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка переключения на раздел 'Булки'")
    public void switchToBunsSectionTest() {
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();
        constructorPage.verifyBunsSectionActive();
    }

    @Test
    @Story("Навигация к разделу 'Соусы")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка переключения на раздел 'Соусы'")
    public void switchToSaucesSectionTest() {
        constructorPage.clickSaucesTab();
        constructorPage.verifySaucesSectionActive();
    }

    @Test
    @Story("Навигация к разделу 'Начинки")
    @Owner("Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка переключения на раздел 'Начинки'")
    public void switchToFillingsSectionTest() {
        constructorPage.clickFillingsTab();
        constructorPage.verifyFillingsSectionActive();
    }

    @After
    @Step("Очистка тестовых данных")
    public void cleanup() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }
}