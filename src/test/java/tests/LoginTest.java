package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

import java.time.Duration;

@Epic("Stellar Burgers UI")
@Feature("Login")
public class LoginTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        step("Открываем страницу логина", () -> {
            open("https://stellarburgers.nomoreparties.site/login");
        });
    }

    @Test
    @Story("Login with valid credentials")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Вход с корректными логином и паролем")
    public void loginWithValidCredentialsTest() {
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail("teran6315@yandex.ru"));
        step("Вводим пароль", () -> loginPage.setPassword("766912"));
        step("Нажимаем кнопку 'Войти'", loginPage::submitLogin);

        step("Проверяем, что открылась главная страница (кнопка 'Личный кабинет' видна)", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").shouldBe(visible);
        });
    }

    @Test
    @Story("Login with invalid credentials")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Попытка входа с некорректным паролем — должна появиться ошибка")
    public void loginWithInvalidPasswordTest() {
        LoginPage loginPage = new LoginPage();
        step("Вводим e-mail", () -> loginPage.setEmail("screwy4@yandex.ru"));
        step("Вводим неверный пароль", () -> loginPage.setPassword("неверныйПароль123"));
        step("Нажимаем кнопку 'Войти'", loginPage::submitLogin);

        step("Проверяем, что появляется сообщение об ошибке", () -> {
            $("p.input__error")
                    .shouldBe(visible, Duration.ofSeconds(8))
                    .shouldHave(text("Некорректный пароль"));
        });
    }
}
