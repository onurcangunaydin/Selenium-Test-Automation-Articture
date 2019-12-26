package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By signInButton = By.xpath("//a[@data-nav-role='signin']");
    private By searchTextBox = By.id("twotabsearchtextbox");
    private By searchButton = By.cssSelector("input[type=submit]");
    private static final String url = "https://www.amazon.com/";

    public HomePage() {
        super(url);
    }

    public LoginPage clickSignInButton(){
        browser.clickTo(signInButton);
        return browser.returnRedirectedPage(new LoginPage());
    }

    public SearchPage search(String keyword){
        browser.type(searchTextBox,keyword);
        browser.clickTo(searchButton);
        return browser.returnRedirectedPage(new SearchPage());

    }











}
