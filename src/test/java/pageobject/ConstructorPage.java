package pageobject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class ConstructorPage {
    public SelenideElement bunsTab = $x("//span[text()='Булки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public SelenideElement saucesTab = $x("//span[text()='Соусы']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public SelenideElement fillingsTab = $x("//span[text()='Начинки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public SelenideElement orderButton = $x("//button[contains(@class,'button_button_type_primary_107Bx') and contains(text(), 'Оформить заказ')]");
}