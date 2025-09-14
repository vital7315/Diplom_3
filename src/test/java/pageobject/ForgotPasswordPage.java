package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class ForgotPasswordPage {
    public SelenideElement loginLink = $x("//a[contains(@href,'/login') and text()='Войти']");
    public void clickLoginLink() { loginLink.click(); }
}
