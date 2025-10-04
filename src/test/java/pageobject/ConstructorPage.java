package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class ConstructorPage {
    private SelenideElement bunsTab = $x("//span[text()='Булки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    private SelenideElement saucesTab = $x("//span[text()='Соусы']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    private SelenideElement fillingsTab = $x("//span[text()='Начинки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    private SelenideElement orderButton = $x("//button[contains(text(), 'Оформить заказ')]");
    private SelenideElement currentSection = $x("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    @Step("Кликнуть на раздел 'Булки'")
    public void clickBunsTab() {
        bunsTab.click();
    }

    @Step("Кликнуть на раздел 'Соусы'")
    public void clickSaucesTab() {
        saucesTab.click();
    }

    @Step("Кликнуть на раздел 'Начинки'")
    public void clickFillingsTab() {
        fillingsTab.click();
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public void verifyBunsSectionActive() {
        currentSection.shouldHave(text("Булки"));
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public void verifySaucesSectionActive() {
        currentSection.shouldHave(text("Соусы"));
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public void verifyFillingsSectionActive() {
        currentSection.shouldHave(text("Начинки"));
    }

    @Step("Проверить, что кнопка 'Оформить заказ' видна")
    public void verifyOrderButtonVisible() {
        orderButton.shouldBe(visible);
    }
}