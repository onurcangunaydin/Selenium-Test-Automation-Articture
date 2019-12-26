import models.Buyer;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyListPage;
import pages.ProductPage;
import pages.SearchPage;

import static constants.Credentials.password;
import static constants.Credentials.username;
import static constants.Keywords.SAMSUNG;
import static org.hamcrest.MatcherAssert.assertThat;

public class AmazonTest extends BaseTest {


    @Test
    public void productShouldBeAddedToWishlistAndDeleted() {
        Buyer buyer = new Buyer(username, password);
        buyer.open(new HomePage()).login().search(SAMSUNG);

        SearchPage searchPage = (SearchPage) buyer.nowLookingAt();
        assertThat("Buyer should search successfully with a keyword"
                , searchPage.isSearchSuccessful(SAMSUNG));

        searchPage.goToSearchNextResultPage();
        assertThat("Buyer should be navigated to second page",
                searchPage.isSelectedPageVisible("2"));

        searchPage.goToProduct(2);
        ProductPage productPage = (ProductPage) buyer.nowLookingAt();
        productPage.addProductToWishlist(0);
        assertThat("Buyer should add product to wish list successfully", productPage.isProductAddedToWishlist());

        productPage.goToMyListPage();
        MyListPage myListPage = (MyListPage) buyer.nowLookingAt();
        myListPage.deleteProductFromWishList(productPage.getProduct());
        assertThat("Buyer should delete product from wish list successfully", myListPage.isProductDeletedFromMyListPage());

        buyer.closeBrowser();

    }
}
