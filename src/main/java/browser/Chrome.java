package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.HashMap;
import java.util.Map;

import static browser.ChromeBase.getDefaultChromeOptions;
import static constants.ConfigNumbers.WAITTIME_MEDIUM;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class Chrome extends SeleniumBrowser {


    private static ChromeOptions getOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = getDefaultChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        return options;
    }


    public Chrome initializeBrowser() {
        ChromeDriverManager.getInstance(CHROME).setup();
        webDriver = new ChromeDriver(getOptions());
        webDriver.manage().window().setSize(new Dimension(1216, 1024));
        return this;
    }

    public void waitForAjax() {
        try {
            ExpectedCondition<Boolean> conditionToCheck = (WebDriver input) -> {
                JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
                boolean stillRunningAjax = (Boolean) jsDriver
                        .executeScript("return document.readyState != \"complete\"");
                return !stillRunningAjax;
            };

            WebDriverWait myWait = new WebDriverWait(webDriver, 10);
            myWait.until(conditionToCheck);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
        }
    }

    public <P extends BasePage> Chrome goTo(P page) {
        this.page = page;
        this.page.setBrowser(this);

        webDriver.navigate().to(page.getUrl());
        waitForUrlContainsInSafe(page.getUrl());

        waitForUrlContains(page.getUrl());
        waitForAjax();
        return this;

    }

    public WebDriverWait getWebDriverWait(int timeoutInSeconds) {
        return new WebDriverWait(webDriver, timeoutInSeconds);
    }

    public void waitForUrlContains(String url) {
        getWebDriverWait(WAITTIME_MEDIUM)
                .until(ExpectedConditions.urlContains(url));
    }

}


