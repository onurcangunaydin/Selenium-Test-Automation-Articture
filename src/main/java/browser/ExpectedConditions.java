package browser;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

import static java.util.Collections.emptyList;

public final class ExpectedConditions {

    private ExpectedConditions() {
        throw new IllegalStateException("Cannot instantiate!");
    }

    static ExpectedCondition<Boolean> enableOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    element.isEnabled();
                    return true;
                } catch (StaleElementReferenceException sere) {
                    return false;
                }
            }

            public String toString() {
                return String.format("element (%s) to become stale", element);
            }
        };
    }

    static ExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedByIn(final By locator, final WebElement parent) {
        return new ExpectedCondition<List<WebElement>>() {

            @Override
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements;
                if (parent != null) {
                    elements = parent.findElements(locator);
                } else {
                    elements = driver.findElements(locator);
                }

                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return emptyList();
                    }
                }
                return !elements.isEmpty() ? elements : null;
            }

            @Override
            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }

    static ExpectedCondition<Boolean> sizeToBe(final By elementLocator, int sizeToChange) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                int elementCount = driver.findElements(elementLocator).size();
                return elementCount != sizeToChange;
            }

            @Override
            public String toString() {
                return "size of the element on page for " + elementLocator;
            }
        };
    }

    static ExpectedCondition<Boolean> containTextAppearInside(final By elementLocator, String textToAppear) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String elementText = driver.findElement(elementLocator).getText();
                return elementText.contains(textToAppear);
            }

            @Override
            public String toString() {
                return "text appear inside of " + elementLocator;
            }
        };
    }

    static ExpectedCondition<Boolean> equalTextAppearInside(final By elementLocator, String textToAppear) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String elementText = driver.findElement(elementLocator).getText();
                return elementText.equals(textToAppear);
            }

            @Override
            public String toString() {
                return "text appear inside of " + elementLocator;
            }
        };
    }


    public static ExpectedCondition<Boolean> textToBe(final WebElement elementLocator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    currentValue = elementLocator.getText();
                    return currentValue.equals(value);
                } catch (WebDriverException wde) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("element found by %s to have text \"%s\". Current text: \"%s\"",
                        elementLocator, value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlToChange(final String urlBefore) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return currentUrl != null && !currentUrl.equals(urlBefore);
            }

            @Override
            public String toString() {
                return String.format("url should not be \"%s\". Current url: \"%s\"", urlBefore, currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlContains(final String fraction) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                return currentUrl != null && currentUrl.contains(fraction);
            }

            @Override
            public String toString() {
                return String.format("url to contain \"%s\". Current url: \"%s\"", fraction, currentUrl);
            }
        };
    }
}
