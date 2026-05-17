package pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends pages.BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        inputText(AppLocators.USERNAME_FIELD, username);
        inputText(AppLocators.PASSWORD_FIELD, password);
        click(AppLocators.LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getText(AppLocators.ERROR_MESSAGE);
    }
}