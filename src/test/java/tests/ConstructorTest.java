package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Epic("Stellar Burgers UI")
@Feature("Burger Constructor")
public class ConstructorTest {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String LOGIN_URL = BASE_URL + "/login";

    private MainPage mainPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        open(LOGIN_URL);

        mainPage = new MainPage();
        loginPage = new LoginPage();

        // Логин через PO методы
        loginPage.login("teran6315@yandex.ru", "766912");
    }

    @Test
    @Story("Navigation between ingredient sections")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка переключения между разделами конструктора бургеров")
    public void constructorSectionNavigationTest() {
        mainPage.clickBunsSection();
        mainPage.clickSaucesSection();
        mainPage.clickFillingsSection();

        // Проверки через PO методы
        mainPage.verifySaucesSectionActive();
    }
}