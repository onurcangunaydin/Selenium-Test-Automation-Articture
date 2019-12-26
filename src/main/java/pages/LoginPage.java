package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By emailBox = By.cssSelector("input[type=email]");
    private By passwordBox = By.cssSelector("input[type=password]");
    private By continueButton = By.cssSelector("input[id=continue]");
    private By signInButton = By.id("signInSubmit");

    public LoginPage() {
    }

    public void fillEmail(String email) {
        browser.type(emailBox, email);

    }

    public void fillPassword(String password) {
        browser.type(passwordBox, password);

    }

    public void clickContinueButton() {
        browser.clickTo(continueButton);
    }

    public void clickSignInButton() {
        browser.clickTo(signInButton);
    }

    public HomePage login(String email, String password) {
        fillEmail(email);
        clickContinueButton();
        fillPassword(password);
        clickSignInButton();
        return browser.returnRedirectedPage(new HomePage());
    }


}
