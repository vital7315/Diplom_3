package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import utils.BrowserConfig;
import utils.Constants;

public class BaseTest {

    @Before
    @Step("Настройка браузера")
    public void setUp() {
        BrowserConfig.setupBrowser();
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    protected void openUrl(String url) {
        Selenide.open(url);
    }
}