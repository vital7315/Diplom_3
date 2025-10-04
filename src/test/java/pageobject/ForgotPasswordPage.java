package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {
    private SelenideElement loginLink = $x("//a[contains(@href,'/login') and text()='Войти']");

    @Step("Кликнуть на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }
}