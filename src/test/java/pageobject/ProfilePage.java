package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class ProfilePage {
    private SelenideElement logoutButton = $x("//button[contains(text(),'Выход')]");
    private SelenideElement constructorButton = $x("//a[@href='/']");
    private SelenideElement logo = $x("//div[contains(@class, 'AppHeader_header__logo')]");
    private SelenideElement profileActiveLink = $x("//a[contains(@class,'AppHeader_header_link_active') and @href='/account']");

    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        logo.click();
    }

    @Step("Проверить, что страница профиля открыта")
    public void verifyProfilePageOpened() {
        logoutButton.shouldBe(visible);
    }

    @Step("Проверить, что ссылка профиля активна")
    public void verifyProfileLinkActive() {
        profileActiveLink.shouldBe(visible);
    }
}