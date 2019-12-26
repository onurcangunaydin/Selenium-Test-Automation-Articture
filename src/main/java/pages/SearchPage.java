package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {

    private By searchResultBox = By.cssSelector(".a-color-state.a-text-bold");
    private By paginationButton = By.xpath("//li[@class='a-normal']/a[contains(text(),'2')]");
    private By products = By.cssSelector(".s-search-results .s-result-item");
    private By productLink = By.cssSelector(".a-link-normal");

    public SearchPage() {
    }

    public boolean isSearchSuccessful(String searchedKeyword) {
        return browser.waitForContainTextToAppearInsideSafe(2, searchResultBox, searchedKeyword);
    }

    public SearchPage goToSearchNextResultPage(){
        browser.clickTo(paginationButton);
        return this;
    }

    public boolean isSelectedPageVisible(String pageId){
        return browser.currentURL().contains(String.format("page=%s", pageId));
    }

    public ProductPage goToProduct(int index){
        WebElement product = browser.waitForAllVisibility(1, products).get(index).findElement(productLink);
        browser.clickTo(product);
        return browser.returnRedirectedPage(new ProductPage());
    }
}
