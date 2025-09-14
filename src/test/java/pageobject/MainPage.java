package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    // Кнопка "Войти в аккаунт" на главной (если не авторизован)
    public SelenideElement loginButton = $x("//button[contains(text(), 'Войти в аккаунт')]");
    // Кнопка "Личный кабинет"
    public SelenideElement personalCabinetButton = $x("//a[@href='/account']");
    // Кнопка "Лента заказов"
    public SelenideElement feedButton = $x("//a[@href='/feed']");

    // Клик по кнопке "Войти в аккаунт" на главной
    public void clickLoginButton() { loginButton.click(); }
    // Клик по кнопке "Личный кабинет"
    public void clickPersonalCabinet() { personalCabinetButton.click(); }
    // Клик по кнопке "Лента заказов"
    public void clickFeed() { feedButton.click(); }
}
