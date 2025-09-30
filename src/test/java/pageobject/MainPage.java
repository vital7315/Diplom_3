package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement loginButton = $x("//button[contains(text(), 'Войти в аккаунт')]");
    private SelenideElement personalCabinetButton = $x("//a[@href='/account']");
    private SelenideElement feedButton = $x("//a[@href='/feed']");
    private SelenideElement constructorButton = $x("//a[@href='/']");
    private SelenideElement logo = $x("//div[contains(@class, 'AppHeader_header__logo')]");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalCabinet() {
        personalCabinetButton.click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        logo.click();
    }

    @Step("Проверить, что пользователь авторизован")
    public void verifyUserLoggedIn() {
        personalCabinetButton.shouldBe(visible);
    }

    @Step("Проверить, что главная страница открыта")
    public void verifyMainPageOpened() {
        loginButton.shouldBe(visible);
    }
}