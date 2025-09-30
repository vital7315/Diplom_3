package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    private SelenideElement emailField = $("[name='name']");
    private SelenideElement passwordField = $("[type='password']");
    private SelenideElement loginButton = $x("//button[contains(text(), 'Войти')]");
    private SelenideElement loginLink = $x("//a[contains(text(), 'Войти')]");
    private SelenideElement passwordError = $x("//p[contains(@class, 'input__error')]");

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Выполнить логин с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Кликнуть на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Проверить отображение ошибки пароля")
    public void verifyPasswordErrorDisplayed() {
        passwordError.shouldBe(visible);
    }

    @Step("Проверить текст ошибки: {expectedError}")
    public void verifyPasswordErrorText(String expectedError) {
        passwordError.shouldHave(text(expectedError));
    }

    @Step("Проверить, что страница логина открыта")
    public void verifyLoginPageOpened() {
        loginButton.shouldBe(visible);
    }
}