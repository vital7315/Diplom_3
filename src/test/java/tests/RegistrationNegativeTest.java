package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Condition.*;

@Epic("Stellar Burgers UI")
@Feature("Registration")
public class RegistrationNegativeTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        step("Открываем страницу регистрации", () -> {
            open("https://stellarburgers.nomoreparties.site/register");
        });
    }

    @Test
    @Story("Регистрация с коротким паролем")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка ошибки при попытке зарегистрироваться с коротким паролем")
    public void registrationWithShortPasswordTest() {
        RegistrationPage regPage = new RegistrationPage();

        step("Вводим имя", () -> regPage.setName("TestUserNeg"));
        step("Вводим e-mail", () -> regPage.setEmail("testneg" + System.currentTimeMillis() + "@mail.ru"));
        step("Вводим короткий пароль", () -> regPage.setPassword("123"));
        step("Пытаемся зарегистрироваться", regPage::submitRegistration);

        step("Проверяем сообщение об ошибке пароля", () -> {
            $x("//p[contains(text(),'Некорректный пароль')]").shouldBe(visible);
        });
    }
}
