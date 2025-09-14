package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    public SelenideElement emailInput = $x("//label[text()='Email']/following-sibling::input");
    public SelenideElement passwordInput = $x("//label[text()='Пароль']/following-sibling::input");
    public SelenideElement loginButton = $x("//button[contains(text(),'Войти')]");

    public void setEmail(String email) {
        emailInput.setValue(email);
    }
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }
    public void submitLogin() {
        loginButton.click();
    }
}
