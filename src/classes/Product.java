package classes;

public abstract class Product {
    private String name;
    private double price;
    private long quantity;

    public Product() {
    }

    public Product(String name, double price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isExpired(){
        return false;
    }
    public boolean isRequiresShipping(){
        return false;
    }
}
