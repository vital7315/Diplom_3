package pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    // Кнопка "Выйти"
    public SelenideElement logoutButton = $x("//button[contains(text(),'Выход')]");
    // Кнопка "Конструктор"
    public SelenideElement constructorButton = $x("//a[@href='/' and contains(@class,'AppHeader_header_link_3D_hX')]");
    // Логотип
    public SelenideElement logo = $x("//a[@href='/' and contains(@class,'AppHeader_header_link_3D_hX')]");
    // Ссылка "Профиль" активная
    public SelenideElement profileActiveLink = $x("//a[contains(@class,'AppHeader_header_link_active_1IkJo') and @href='/account']");
}
