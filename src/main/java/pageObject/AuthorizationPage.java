package pageObject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {

    private final SelenideElement authorizationButton = $x("//button[contains(@class,'btn-primary') " +
            "and text()='Login']");

    private static final String AUTHORIZATION_FIELD_PATTERN = "//input[contains(@class,'form-control') " +
            "and @placeholder ='%s']";

    public void clickOnLoginButton(){
        authorizationButton.scrollIntoView(true).click();
    }

    public AuthorizationPage setUserNameField(String userName) {
        $x(String.format(AUTHORIZATION_FIELD_PATTERN, "UserName")).setValue(userName);
        return this;
    }

    public AuthorizationPage setPasswordField(String password) {
        $x(String.format(AUTHORIZATION_FIELD_PATTERN, "Password")).setValue(password);
        return this;
    }

}
