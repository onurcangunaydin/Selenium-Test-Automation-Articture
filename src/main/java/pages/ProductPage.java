package pages;

import models.Product;
import org.openqa.selenium.By;

import static constants.Keywords.ITEM_ADDED_TO_WISHLIST;
import static constants.Keywords.ITEM_ALREADY_IN_WISHLIST;

public class ProductPage extends BasePage {

    private Product product;
    private By addToWishlistButton = By.id("add-to-wishlist-button");
    private By shoppingListItem = By.cssSelector(".a-dropdown-item");
    private By productTitle = By.id("productTitle");
    private By viewYourListButton = By.xpath("//span[contains(text(),'View Your List')]");
    private By addedToWishlistMessageBox = By.className("w-success-msg");

    public ProductPage() {
    }

    public Product setProductInfo() {
        String productName = browser.waitForText(productTitle);
        product = new Product(productName);
        return product;
    }

    public void addProductToWishlist(int shoppingListIndex) {
        setProductInfo();
        browser.clickTo(addToWishlistButton);
        browser.waitForAllVisibility(1, shoppingListItem).get(shoppingListIndex).click();

    }

    public Product getProduct() {
        return product;
    }

    public boolean isProductAddedToWishlist(){
        boolean isItemAddedToCart = browser.waitForContainTextToAppearInsideSafe(2,addedToWishlistMessageBox, ITEM_ADDED_TO_WISHLIST);
        boolean isItemAlreadyAddedToCart = browser.waitForContainTextToAppearInsideSafe(2,addedToWishlistMessageBox, ITEM_ALREADY_IN_WISHLIST);
        return isItemAddedToCart || isItemAlreadyAddedToCart;
    }

    public MyListPage goToMyListPage(){
        browser.clickTo(viewYourListButton);
        return browser.returnRedirectedPage(new MyListPage());
    }

}
