package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Epic("Stellar Burgers UI")
@Feature("Logout")
public class LogoutTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        step("Открываем страницу логина", () -> {
            open("https://stellarburgers.nomoreparties.site/login");
        });
        step("Авторизация перед тестом", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setEmail("teran6315@yandex.ru");
            loginPage.setPassword("766912");
            loginPage.submitLogin();
        });
    }

    @Test
    @Story("Logout from profile")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Выход из профиля пользователя")
    public void logoutFromProfileTest() {
        step("Переходим в личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").click();
        });
        step("Нажимаем кнопку 'Выход'", () -> {
            $x("//button[contains(text(),'Выход')]").click();
        });
        step("Проверяем, что находимся на странице входа", () -> {
            $("h2").shouldHave(text("Вход"));
        });
    }
}
