package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

@Epic("Stellar Burgers UI")
@Feature("Регистрация")
public class RegistrationNegativeTest extends BaseTest {
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        registrationPage = new RegistrationPage();
        open("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    @Story("Регистрация с коротким паролем")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка ошибки при попытке зарегистрироваться с коротким паролем")
    public void registrationWithShortPasswordTest() {
        registrationPage.setName("TestUserNeg");
        registrationPage.setEmail("testneg" + System.currentTimeMillis() + "@mail.ru");
        registrationPage.setPassword("123");
        registrationPage.clickRegisterButton();
        registrationPage.verifyPasswordErrorDisplayed();
        registrationPage.verifyPasswordErrorText("Некорректный пароль");
    }
}