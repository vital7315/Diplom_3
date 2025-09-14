package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.LoginPage;
import pageobject.RegistrationPage;
import pageobject.ForgotPasswordPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

/**
 * Проверка всех вариантов входа в аккаунт пользователя на сайте Stellar Burgers.
 * Реализованы сценарии:
 * 1. Вход по кнопке "Войти в аккаунт" на главной странице.
 * 2. Вход через кнопку "Личный кабинет" на главной странице.
 * 3. Вход через ссылку "Войти" внизу формы регистрации.
 * 4. Вход через ссылку "Войти" внизу формы восстановления пароля.
 */
@Epic("Stellar Burgers UI")
@Feature("Login")
@Owner("Твоё Имя")
public class LoginDifferentWaysTest {

    private final String EMAIL = "screwy4@yandex.ru";
    private final String PASSWORD = "12345678";

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @Story("Login via main page button")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        step("Открываем главную страницу", () -> open("https://stellarburgers.nomoreparties.site/"));
        MainPage mainPage = new MainPage();
        step("Кликаем 'Войти в аккаунт'", mainPage::clickLoginButton);
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail(EMAIL));
        step("Вводим пароль", () -> loginPage.setPassword(PASSWORD));
        step("Нажимаем 'Войти'", loginPage::submitLogin);
        step("Проверяем, что зашли — есть Личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }

    @Test
    @Story("Login via 'Личный кабинет'")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через кнопку 'Личный кабинет' на главной странице")
    public void loginViaPersonalCabinetTest() {
        step("Открываем главную страницу", () -> open("https://stellarburgers.nomoreparties.site/"));
        MainPage mainPage = new MainPage();
        step("Кликаем 'Личный кабинет'", mainPage::clickPersonalCabinet);
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail(EMAIL));
        step("Вводим пароль", () -> loginPage.setPassword(PASSWORD));
        step("Нажимаем 'Войти'", loginPage::submitLogin);
        step("Проверяем, что зашли — есть Личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }

    @Test
    @Story("Login from Registration form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через ссылку 'Войти' в форме регистрации")
    public void loginFromRegistrationPageTest() {
        step("Открываем страницу регистрации", () -> open("https://stellarburgers.nomoreparties.site/register"));
        RegistrationPage regPage = new RegistrationPage();
        step("Кликаем 'Войти' внизу формы", regPage::clickLoginLink);
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail(EMAIL));
        step("Вводим пароль", () -> loginPage.setPassword(PASSWORD));
        step("Нажимаем 'Войти'", loginPage::submitLogin);
        step("Проверяем, что зашли — есть Личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }

    @Test
    @Story("Login from ForgotPassword form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Вход через ссылку 'Войти' в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        step("Открываем страницу восстановления пароля", () -> open("https://stellarburgers.nomoreparties.site/forgot-password"));
        ForgotPasswordPage forgotPage = new ForgotPasswordPage();
        step("Кликаем 'Войти' внизу формы", forgotPage::clickLoginLink);
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail(EMAIL));
        step("Вводим пароль", () -> loginPage.setPassword(PASSWORD));
        step("Нажимаем 'Войти'", loginPage::submitLogin);
        step("Проверяем, что зашли — есть Личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }
}
