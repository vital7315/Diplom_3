package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    public SelenideElement emailField = $("[name='name']");
    public SelenideElement passwordField = $("[type='password']");
    public SelenideElement loginButton = $x("//button[contains(text(), 'Войти')]");
    public SelenideElement loginLink = $x("//a[contains(text(), 'Войти')]");

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void submitLogin() {
        loginButton.click();
    }

    @Step("Выполнить логин с email: {email} и паролем")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitLogin();
    }

    @Step("Кликнуть на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }
}