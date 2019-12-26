package browser;

import exception.TestException;
import models.Buyer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;

import static browser.ExpectedConditions.*;
import static constants.ConfigNumbers.*;
import static java.util.Collections.emptyList;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static utils.URLUtils.decodeUrl;

public class SeleniumBrowser {

    protected BasePage page;
    protected Buyer buyer;
    protected WebDriver webDriver;


    public WebElement waitForPresence(int timeoutInSeconds, By by) {
        return getWebDriverWait(timeoutInSeconds)
                .until(presenceOfElementLocated(by));
    }

    public List<WebElement> waitForAllPresence(int timeoutInSeconds, By by) {
        return getWebDriverWait(timeoutInSeconds)
                .until(presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitForAllPresenceInSafe(int timeoutInSeconds, By by) {
        try {
            return getWebDriverWait(timeoutInSeconds)
                    .until(presenceOfAllElementsLocatedBy(by));
        } catch (TimeoutException te) {
            return emptyList();
        }
    }

    public WebElement waitForPresenceInSafe(int timeoutInSeconds, By by) {
        if (isElementPresent(timeoutInSeconds, by)) {
            return webDriver.findElement(by);
        } else {
            return null;
        }
    }

    public WebElement waitForVisibility(int timeoutInSeconds, By by) {
        return getWebDriverWait(timeoutInSeconds)
                .until(visibilityOfElementLocated(by));
    }

    public WebElement waitForVisibility(By by) {
        return waitForVisibility(WAITTIME_MEDIUM, by);
    }

    public WebElement waitForVisibility(int timeoutInSeconds, WebElement element) {
        return getWebDriverWait(timeoutInSeconds)
                .until(visibilityOf(element));
    }

    public WebElement waitForVisibilityInSafe(int timeoutInSeconds, By by) {
        try {
            return waitForVisibility(timeoutInSeconds, by);
        } catch (TimeoutException te) {
            return null;
        }
    }

    public List<WebElement> waitForAllVisibility(int timeoutInSeconds, By by) {
        try {
            return getWebDriverWait(timeoutInSeconds)
                    .until(visibilityOfAllElementsLocatedBy(by));
        } catch (StaleElementReferenceException sere) {
            wait(WAITTIME_TOO_SMALL);
            return getWebDriverWait(timeoutInSeconds)
                    .until(visibilityOfAllElementsLocatedBy(by));
        }
    }

    public List<WebElement> waitForAllVisibilityInSafe(int timeoutInSeconds, By by) {
        try {
            return waitForAllVisibility(timeoutInSeconds, by);
        } catch (StaleElementReferenceException sere) {
            return waitForAllVisibility(timeoutInSeconds, by);
        } catch (TimeoutException te) {
            return emptyList();
        }
    }

    public List<WebElement> waitForAllVisibilityInside(int timeoutInSeconds, By by, WebElement element) {
        return getWebDriverWait(timeoutInSeconds)
                .until(visibilityOfAllElementsLocatedByIn(by, element));
    }

    public List<WebElement> waitForAllVisibilityInsideInSafe(int timeoutInSeconds, By by, WebElement element) {
        try {
            return waitForAllVisibilityInside(timeoutInSeconds, by, element);
        } catch (TimeoutException te) {
            return emptyList();
        }
    }

    public void waitForElementToHide(int timeoutInSeconds, By elementLocator) {
        getWebDriverWait(timeoutInSeconds)
                .until(invisibilityOfElementLocated(elementLocator));
    }

    public void waitForElementToHide(int timeoutInSeconds, WebElement element) {
        getWebDriverWait(timeoutInSeconds)
                .until(invisibilityOf(element));
    }

    public boolean waitForElementToHideInSafe(int timeoutInSeconds, By elementLocator) {
        try {
            waitForElementToHide(timeoutInSeconds, elementLocator);
            return true;
        } catch (WebDriverException wde) {
            return false;
        }
    }

    public void waitForTextToChange(int timeoutInSeconds, By by, String textBefore) {
        getWebDriverWait(timeoutInSeconds)
                .until(invisibilityOfElementWithText(by, textBefore));
    }

    public void waitForTextToBe(int timeoutInSeconds, By by, String textToBe) {
        getWebDriverWait(timeoutInSeconds)
                .until(textToBe(by, textToBe));
    }

    public WebElement waitForClickableOf(int timeoutInSeconds, WebElement elementLocator) {
        return getWebDriverWait(timeoutInSeconds)
                .until(elementToBeClickable(elementLocator));
    }

    public void waitForEnableOf(int timeoutInSeconds, WebElement elementLocator) {
        getWebDriverWait(timeoutInSeconds)
                .until(enableOf(elementLocator));
    }

    public String waitForText(By by) {
        return waitForVisibility(WAITTIME_MEDIUM, by).getText();
    }

    public String waitForText(WebElement element) {
        return waitForVisibility(WAITTIME_ELEMENTOCCURENCE, element).getText();
    }

    public WebElement waitForVisibilityForNestedElement(int timeoutInSeconds, WebElement parentElement, By childLocator) {
        getWebDriverWait(timeoutInSeconds)
                .until(visibilityOfNestedElementsLocatedBy(parentElement, childLocator));
        return parentElement.findElement(childLocator);
    }


    public void waitForElementToDisappear(int timeoutInSeconds, WebElement element) {
        getWebDriverWait(timeoutInSeconds)
                .until(stalenessOf(element));
    }

    public boolean waitUntilSizeChanges(int timeoutInSeconds, By elementLocator, int sizeToChange) {
        return getWebDriverWait(timeoutInSeconds)
                .until(sizeToBe(elementLocator, sizeToChange));
    }

    public boolean waitForContainTextToAppearInsideSafe(int timeoutInSeconds, By elementLocatorToCheck, String text) {
        try {
            getWebDriverWait(timeoutInSeconds)
                    .until(containTextAppearInside(elementLocatorToCheck, text));
            return true;
        } catch (WebDriverException wde) {
            return false;
        }
    }

    public boolean waitForEqualTextToAppearInside(int timeoutInSeconds, By elementLocatorToCheck, String text) {
        return getWebDriverWait(timeoutInSeconds)
                .until(equalTextAppearInside(elementLocatorToCheck, text));
    }

    public void waitForWindowsSizeToBe(int size, int timeoutInSeconds) {
        try {
            getWebDriverWait(timeoutInSeconds)
                    .until(numberOfWindowsToBe(size));
            waitUntilReady();
        } catch (RuntimeException rte) {
            logError(size + " number of window cannot found after wait: " + rte);
        }
    }

    public void waitForElementToGetAttribute(int timeoutInSeconds, By elementLocator, String attribute, String value) {
        getWebDriverWait(timeoutInSeconds)
                .until(attributeContains(elementLocator, attribute, value));
    }

    public void waitForElementToGetAttribute(int timeoutInSeconds, WebElement element, String attribute, String value) {
        getWebDriverWait(timeoutInSeconds)
                .until(attributeContains(element, attribute, value));
    }

    public void waitForElementToBeAttribute(int timeoutInSeconds, By elementLocator, String attribute, String value) {
        getWebDriverWait(timeoutInSeconds)
                .until(attributeToBe(elementLocator, attribute, value));
    }

    public boolean isElementContainsAttribute(int timeoutInSeconds, WebElement element, String attribute, String value) {
        try {
            return getWebDriverWait(timeoutInSeconds)
                    .until(attributeContains(element, attribute, value));
        } catch (TimeoutException te) {
            return false;
        }
    }

    public void waitForUrlContains(int timeoutInSeconds, String urlTextBefore) {
        getWebDriverWait(timeoutInSeconds)
                .until(not(org.openqa.selenium.support.ui.ExpectedConditions.urlContains(urlTextBefore)));
    }

    public void waitForUrlToBe(int timeoutInSeconds, String urlToBe) {
        getWebDriverWait(timeoutInSeconds)
                .until(urlToBe(urlToBe));
    }

    public void waitForUrlContains(String url) {
        getWebDriverWait(WAITTIME_MEDIUM)
                .until(ExpectedConditions.urlContains(url));
    }

    public void waitForUrlContainsInSafe(String url) {
        try {
            getWebDriverWait(WAITTIME_ELEMENTOCCURENCE)
                    .until(ExpectedConditions.urlContains(url));
        } catch (WebDriverException wde) {
            logError("URL is not the same!");
        }
    }

    public void waitForUrlToChange(String urlBefore) {
        getWebDriverWait(WAITTIME_MEDIUM)
                .until(urlToChange(urlBefore));
    }

    public void waitForAjax() {
        try {
            ExpectedCondition<Boolean> conditionToCheck = (WebDriver input) -> {
                JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
                boolean stillRunningAjax = (Boolean) jsDriver
                        .executeScript("return (window.jQuery != undefined && ($(':animated').length != 0)) || document.readyState != \"complete\"");
                return !stillRunningAjax;
            };

            WebDriverWait myWait = getWebDriverWait(WAITTIME_TIMEOUT);
            myWait.until(conditionToCheck);
        } catch (RuntimeException rte) {
            logError(rte.toString());
        }
    }


    public void waitForCheckoutGeneralPageLoading() {
        waitForElementToHide(WAITTIME_TIMEOUT, By.className("checkoutGeneralPageLoading"));
    }

    public void waitForListingPageLoading() {
        waitForElementToHide(WAITTIME_TIMEOUT, By.className("loading"));
    }


    public boolean isElementPresent(By by) {
        return !webDriver.findElements(by).isEmpty();
    }

    public boolean isElementPresent(int timeoutInSeconds, By by) {
        try {
            waitForPresence(timeoutInSeconds, by);
            return true;
        } catch (TimeoutException toe) {
            return false;
        }
    }

    public boolean isElementPresentInside(SearchContext element, By by) {
        return !element.findElements(by).isEmpty();
    }

    public boolean isElementVisible(By by) {
        List<WebElement> elements = webDriver.findElements(by);
        try {
            if (!elements.isEmpty()) {
                return elements.get(FIRST_INDEX).isDisplayed();
            } else {
                return false;
            }
        } catch (StaleElementReferenceException sere) {
            return isElementVisible(by);
        }
    }

    public boolean isElementVisible(WebElement webElement) {
        if (webElement != null) {
            try {
                return webElement.isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException ex) {
                logError("Element is not visible! " + ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isElementVisible(int timeoutInSeconds, By by) {
        try {
            waitForVisibility(timeoutInSeconds, by);
            return true;
        } catch (WebDriverException wde) {
            return false;
        }
    }

    public boolean isElementVisibleInside(SearchContext parentElement, By by) {
        try {
            WebElement element = parentElement.findElement(by);
            return isElementVisible(element);
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public boolean isAlertVisible() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public WebDriverWait getWebDriverWait(int timeoutInSeconds) {
        return new WebDriverWait(webDriver, timeoutInSeconds);
    }


    public void waitMs(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitUntilReady();
    }

    public boolean waitForDropdownToChange(int timeoutInSeconds, By optionElement, String expected) {
        return getWebDriverWait(timeoutInSeconds)
                .until(org.openqa.selenium.support.ui.ExpectedConditions.textToBe(optionElement, expected));
    }

    public void waitUntilReady() {
        try {
            waitForAjax();
        } catch (WebDriverException wde) {
            logError("Exception on waiting for loading: " + wde);
        }
    }

    public void goBack() {
        webDriver.navigate().back();
        waitUntilReady();
    }

    public void wait(int seconds) {
        waitMs(seconds * 1000);
    }

    public final SeleniumBrowser changePage(BasePage page) {
        this.page = page;
        this.page.setBrowser(this);
        return this;
    }

    public final BasePage page() {
        return this.page;
    }

    public String currentURL() {
        try {
            String currentUrl = webDriver.getCurrentUrl();
            return decodeUrl(currentUrl);
        } catch (TimeoutException toe) {
            logError("Timeout when trying to get currentUrl!");
            return null;
        } catch (WebDriverException wde) {
            logError("Exception occured while trying to get currentUrl, " + wde);
            return null;
        }
    }

    public <T extends BasePage> T returnRedirectedPage(T pageWillBeReturned) {
        changePage(pageWillBeReturned);
        return pageWillBeReturned;
    }

    public void type(By by, String text) {
        try {
            type(waitForVisibility(WAITTIME_MEDIUM, by), text);
        } catch (StaleElementReferenceException sere) {
            type(by, text);
        }
    }

    public void type(WebElement element, String text) {
        scrollTo(element);
        waitUntilReady();
        element.sendKeys(text);
        waitUntilReady();
    }

    public void clickTo(WebElement element) {
        try {
            waitForClickableOf(WAITTIME_MEDIUM, element);
            element.click();
            waitUntilReady();
        } catch (WebDriverException wde) {
            logError("WebDriver exception: " + wde);
            if (wde.getMessage().contains("Other element would receive the click")) {
                scrollTo(element, 350);
                element.click();
                waitUntilReady();
            } else if (wde.getMessage().contains("is not clickable at point")) {
                wait(1);
                scrollTo(element, 350);
                element.click();
                waitUntilReady();
            } else if (wde.getMessage().contains("could not be scrolled into view")) {
                element.click();
                waitUntilReady();
            } else {
                throw wde;
            }
        }
    }

    public void clickTo(By by) {
        WebElement element = waitForClickableOf(WAITTIME_MEDIUM, by);
        try {
            element.click();
            waitUntilReady();
        } catch (WebDriverException wde) {
            logError("WebDriver exception: " + wde);
            if (wde.getMessage().contains("Other element would receive the click")) {
                scrollTo(element, 350);
                element.click();
                waitUntilReady();
            } else if (wde.getMessage().contains("is not clickable at point")) {
                wait(1);
                scrollTo(element, 350);
                element.click();
                waitUntilReady();
            } else if (wde.getMessage().contains("could not be scrolled into view")) {
                element.click();
                waitUntilReady();
            } else {
                throw wde;
            }
        }
    }

    private void logError(String s) {
    }

    public void scrollTo(WebElement element) {
        waitForVisibility(WAITTIME_MEDIUM, element);
        scrollTo(element, 300);
    }

    private void scrollTo(WebElement element, int margin) {
        scrollTo(element.getLocation().x, element.getLocation().y - margin);
    }

    public void scrollTo(int x, int y) {
        js("scrollTo(" + x + "," + y + ");");
    }

    public void scrollDownInsideOf(WebElement element, int scrollDown) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollTop = arguments[1];", element, scrollDown);
        waitUntilReady();
    }

    public Object js(String script) {
        return js(script, null);
    }

    public Object js(String script, WebElement arg) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Object response = "";
        try {
            response = js.executeScript(script, arg);
        } catch (WebDriverException wde) {
            logError("Cannot execute script, " + wde);
        }

        waitUntilReady();
        return response;
    }

    public WebElement waitForClickableOf(int timeoutInSeconds, By elementLocator) {
        return getWebDriverWait(timeoutInSeconds)
                .until(elementToBeClickable(elementLocator));
    }

    public void clickMultipleTimes(WebElement element, By controlBy) {
        int count = 0;
        do {
            clickTo(element);
            count++;
        } while (!isElementVisible(WAITTIME_TOO_SMALL, controlBy) && isElementVisible(element) && count <= TRY_COUNT);

        if (!isElementVisible(controlBy) && !isElementVisible(By.className("login-page"))) {
            throw new TestException(String.format("%s couldn't receive the click", controlBy));
        }
    }

    public BasePage goToPage(BasePage page) {
        if (page().getUrl().contains(page.getUrl())) {
            return visitor().nowLookingAt();
        } else {
            goTo(page);
            return page;
        }
    }

    public <P extends BasePage> SeleniumBrowser goTo(P page) {
        this.page = page;
        this.page.setBrowser(this);

        webDriver.navigate().to(page.getUrl());
        waitForUrlContainsInSafe(page.getUrl());

        waitForUrlContains(page.getUrl());
        waitForAjax();
        return this;

    }

    public void closeBrowser() {
        webDriver.close();
    }

    public final Buyer visitor() {
        return this.buyer;
    }

}
