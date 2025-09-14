package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BrowserConfig {

    public static void setUp(String browser) {
        // Настройки Selenide
        Configuration.browser = browser;
        Configuration.timeout = 8000;
        Configuration.browserSize = "1920x1080";

        // Настройка Allure для Selenide
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)    // Делать скриншоты при падении тестов
                .savePageSource(true) // Сохранять исходный код страницы
        );
    }
}