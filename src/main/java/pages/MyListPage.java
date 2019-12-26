package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class MyListPage extends BasePage {
    private By products = By.xpath("//*[contains(@id,'itemMain')]");
    private By deleteFromListButton = By.cssSelector("input[name*=deleteItem]");
    private By deletedIcon = By.xpath("//div[contains(text(), 'Deleted')]");
    private List<WebElement> productList;

    public MyListPage() {
    }

    public WebElement getProductFromMyList(Product product) {
        productList = browser.waitForAllVisibility(2, products);
        return productList.stream().filter(p -> p.getText().contains(product.getProductTitle())).collect(Collectors.toList()).get(0);
    }

    public MyListPage deleteProductFromWishList(Product product) {
        getProductFromMyList(product).findElement(deleteFromListButton).click();
        return this;
    }

    public boolean isProductDeletedFromMyListPage() {
        return browser.waitForVisibility(deletedIcon).isDisplayed();
    }

    ;
}
