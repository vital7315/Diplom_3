package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BrowserConfig {

    public static void setupBrowser() {
        String browser = System.getProperty("browser", Constants.DEFAULT_BROWSER);
        String browserSize = System.getProperty("browserSize", Constants.DEFAULT_BROWSER_SIZE);
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.headless = headless;
        Configuration.timeout = 10000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }
}