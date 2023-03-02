package pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BookStorePage {
    private final SelenideElement loginLink = $x("//button[contains(@class,'btn-primary') " +
            "and @id='login']");

    public AuthorizationPage clickOnLoginLink (){
        loginLink.click();
        return new AuthorizationPage();
    }

}
