package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    public SelenideElement nameInput = $x("//label[text()='Имя']/following-sibling::input");
    public SelenideElement emailInput = $x("//label[text()='Email']/following-sibling::input");
    public SelenideElement passwordInput = $x("//label[text()='Пароль']/following-sibling::input");
    public SelenideElement registerButton = $x("//button[contains(text(),'Зарегистрироваться')]");
    public SelenideElement passwordError = $x("//p[contains(@class, 'input__error') or contains(text(), 'Некорректный пароль')]");
    public SelenideElement emailError = $x("//p[contains(@class, 'input__error') and contains(text(), 'Email')]");
    public SelenideElement loginLink = $x("//a[@href='/login']");

    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void submitRegistration() {
        registerButton.click();
    }

    // Клик по ссылке "Войти" на странице регистрации
    public void clickLoginLink() {
        loginLink.click();
    }

    // Получить текст ошибки пароля
    public String getPasswordErrorText() {
        return passwordError.getText();
    }

    // Получить текст ошибки email (если есть)
    public String getEmailErrorText() {
        return emailError.getText();
    }

    // Метод для полной регистрации пользователя
    public void registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        submitRegistration();
    }

    // Метод для проверки успешной регистрации (должен быть перенаправлен на логин)
    public void verifyRegistrationSuccess() {
        // Проверяем, что произошло перенаправление на страницу логина
        webdriver().driver().getWebDriver().getCurrentUrl().contains("/login");
    }

    // Метод для проверки отображения ошибки пароля
    public void verifyPasswordErrorDisplayed() {
        passwordError.shouldBe(com.codeborne.selenide.Condition.visible);
    }
}