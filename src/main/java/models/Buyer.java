package models;

import browser.Chrome;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class Buyer {
    public Chrome browser;
    private String username;
    private String password;

    public Buyer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public <P extends BasePage> Buyer open(P page) {
        this.browser = new Chrome().initializeBrowser().goTo(page);
        return this;
    }
    public Buyer openHomePage() {
        HomePage homePage = new HomePage();
        open(homePage);
        return this;
    }

    public Buyer changePage(BasePage page) {
        this.browser().changePage(page);
        return this;
    }

    public Buyer goBack() {
        this.browser().goBack();
        return this;
    }

    public Chrome browser() {
        return browser;
    }

    public Buyer login(){
        HomePage homePage = browser.returnRedirectedPage(new HomePage());
        LoginPage loginPage = homePage.clickSignInButton();
        loginPage.login(this.username,this.password);
        return this;
    }
    public Buyer search(String keyword){
        HomePage homePage = (HomePage) nowLookingAt();
        homePage.search(keyword);
        return this;
    }

    public void closeBrowser() {
        browser.closeBrowser();
    }

    public BasePage nowLookingAt() {
        return browser.page();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
