package ui;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageObject.AuthorizationPage;
import pageObject.BookStorePage;
import utils.GetURL;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {
    private static final String LOGIN_URL = "https://demoqa.com/login";
    private static final String USERNAME = "Eugene";
    private static final String PASSWORD = "!!11aaAA<<";
    private static final String DEMOQA_URL = "https://demoqa.com/books";
    private static BookStorePage bookStorePage;
    private static AuthorizationPage authorizationPage;

    @BeforeAll
    public static void initPage() {
        open(DEMOQA_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        bookStorePage = new BookStorePage();
        authorizationPage = new AuthorizationPage();
    }
    @DisplayName("Checking to login with right username and password")
    @Test
    public void testUserIsAbleToLogin() {
        bookStorePage.clickOnLoginLink()
                .setUserNameField(USERNAME)
                .setPasswordField(PASSWORD)
                .clickOnLoginButton();
        Assertions.assertNotEquals(LOGIN_URL,GetURL.getCurrentURL(),"Account login failed");
    }

    @DisplayName("Checking to login with wrong username and password")
    @ParameterizedTest
    @CsvSource(value = {"Eugene, 12345qwerty", "Pavel,11!!aaAA<<"})
    void testIsUserCantLogin(String username, String password) {
        bookStorePage.clickOnLoginLink()
                .setUserNameField(username)
                .setPasswordField(password)
                .clickOnLoginButton();
        Assertions.assertEquals(LOGIN_URL,GetURL.getCurrentURL(),
                "The user was able to login with invalid credentials");
    }

}
