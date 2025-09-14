package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Epic("Stellar Burgers UI")
@Feature("Constructor Tabs")
public class ConstructorTest {

    @Before
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://stellarburgers.nomoreparties.site/login");
        $("[name='name']").setValue("teran6315@yandex.ru");
        $("[type='password']").setValue("766912");
        $("button.button_button_type_primary__1O7Bx").click();

        // Ждем появления главной страницы, чтобы лоадеры и overlay ушли
        $("main").shouldBe(visible);
        // Если на сайте бывает overlay — добавить ожидание его исчезновения:
        // $$(".overlay, .modal, .cookie-banner").forEach(e -> e.shouldBe(hidden));
    }

    @Test
    @Story("Go to buns tab")
    @Owner("Твоё Имя")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Клик по табу 'Булки' и проверка, что таб активен")
    public void goToBunsTabTest() {
        step("Переходим на главную, убеждаемся что страница прогружена", () -> {
            $("main").shouldBe(visible);
        });

        SelenideElement bunsTab = $x("//span[text()='Булки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");

        step("Если таб уже активен, пропускаем клик", () -> {
            if (!bunsTab.has(cssClass("tab_tab_type_current__2BEPc"))) {
                bunsTab.shouldBe(visible).click();
            }
        });

        step("Проверяем, что таб 'Булки' активен", () -> {
            bunsTab.shouldHave(cssClass("tab_tab_type_current__2BEPc"));
        });
    }
}
