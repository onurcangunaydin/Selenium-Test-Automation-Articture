package pages;

import browser.SeleniumBrowser;

public class BasePage {


    private String url;
    protected SeleniumBrowser browser;

    public BasePage() {

    }

    public BasePage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setBrowser(SeleniumBrowser browser) {
        this.browser = browser;
    }


}
