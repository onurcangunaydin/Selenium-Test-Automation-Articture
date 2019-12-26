package models;

public class Product {
    private  String productTitle;

    public Product(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
