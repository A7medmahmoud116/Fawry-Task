package classes;

public class CartItem {
    private Product product;
    private long quantity;

    public CartItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
