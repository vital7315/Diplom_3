package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Epic("Stellar Burgers UI")
@Feature("Профиль")
public class ProfileTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        step("Открываем страницу логина", () -> {
            open("https://stellarburgers.nomoreparties.site/login");
        });
        step("Логинимся перед тестом", () -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setEmail("teran6315@yandex.ru");
            loginPage.setPassword("766912");
            loginPage.submitLogin();
        });
    }

    @Test
    @Story("Переход в личный кабинет")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода в ЛК и наличия кнопки 'Выход'")
    public void goToPersonalCabinetTest() {
        step("Переходим в Личный кабинет", () -> {
            $("a.AppHeader_header__link__3D_hX[href='/account']").click();
        });
        step("Проверяем, что видна кнопка 'Выход'", () -> {
            $x("//button[contains(text(),'Выход')]").shouldBe(visible);
        });
    }
}
