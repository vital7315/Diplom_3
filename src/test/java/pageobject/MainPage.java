package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    // Кнопка "Войти в аккаунт" на главной (если не авторизован)
    public SelenideElement loginButton = $x("//button[contains(text(), 'Войти в аккаунт')]");
    // Кнопка "Личный кабинет"
    public SelenideElement personalCabinetButton = $x("//a[@href='/account']");
    // Кнопка "Лента заказов"
    public SelenideElement feedButton = $x("//a[@href='/feed']");
    // Разделы конструктора
    public SelenideElement bunsSection = $x("//span[contains(text(), 'Булки')]");
    public SelenideElement saucesSection = $x("//span[contains(text(), 'Соусы')]");
    public SelenideElement fillingsSection = $x("//span[contains(text(), 'Начинки')]");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() { loginButton.click(); }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalCabinet() { personalCabinetButton.click(); }

    @Step("Клик по кнопке 'Лента заказов'")
    public void clickFeed() { feedButton.click(); }

    @Step("Клик по разделу 'Булки'")
    public void clickBunsSection() { bunsSection.click(); }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesSection() { saucesSection.click(); }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsSection() { fillingsSection.click(); }

    @Step("Проверить, что пользователь авторизован")
    public void verifyUserLoggedIn() {
        personalCabinetButton.shouldBe(visible);
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public void verifySaucesSectionActive() {
        saucesSection.shouldHave(cssClass("tab_tab_type_current__2BEPc"));
    }
}