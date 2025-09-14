package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Epic("Stellar Burgers UI")
@Feature("Login from Main Page")
public class LoginFromMainPageTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        step("Открываем главную страницу", () -> {
            open("https://stellarburgers.nomoreparties.site/");
        });
    }

    @Test
    @Story("Login via 'Войти в аккаунт' с главной страницы")
    @Owner("Твоё Имя") // <-- замени на своё имя, если нужно
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка логина через главную страницу по кнопке 'Войти в аккаунт'")
    public void loginFromMainPageTest() {
        MainPage mainPage = new MainPage();

        step("Нажать 'Войти в аккаунт' на главной", () -> {
            mainPage.loginButton.click();
        });

        LoginPage loginPage = new LoginPage();
        step("Ввести e-mail", () -> loginPage.setEmail("teran6315@yandex.ru"));
        step("Ввести пароль", () -> loginPage.setPassword("766912"));
        step("Нажать 'Войти'", loginPage::submitLogin);

        step("Проверить, что после логина видна кнопка 'Личный кабинет'", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }
}
