package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {
    private SelenideElement nameInput = $x("//label[text()='Имя']/following-sibling::input");
    private SelenideElement emailInput = $x("//label[text()='Email']/following-sibling::input");
    private SelenideElement passwordInput = $x("//label[text()='Пароль']/following-sibling::input");
    private SelenideElement registerButton = $x("//button[contains(text(),'Зарегистрироваться')]");
    private SelenideElement passwordError = $x("//p[contains(@class, 'input__error')]");
    private SelenideElement loginLink = $x("//a[@href='/login']");

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        nameInput.setValue(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Кликнуть по ссылке 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Зарегистрировать пользователя: {name}, {email}")
    public void registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверить отображение ошибки пароля")
    public void verifyPasswordErrorDisplayed() {
        passwordError.shouldBe(visible);
    }

    @Step("Проверить текст ошибки пароля: {expectedError}")
    public void verifyPasswordErrorText(String expectedError) {
        passwordError.shouldHave(text(expectedError));
    }

    @Step("Проверить успешную регистрацию")
    public void verifyRegistrationSuccess() {
        // После успешной регистрации должна открыться страница логина
        $x("//button[contains(text(), 'Войти')]").shouldBe(visible);
    }

    @Step("Проверить, что страница регистрации открыта")
    public void verifyRegistrationPageOpened() {
        registerButton.shouldBe(visible);
    }
}