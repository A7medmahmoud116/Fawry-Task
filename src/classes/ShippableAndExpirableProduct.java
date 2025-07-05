package classes;

import java.util.Date;

public class ShippableAndExpirableProduct extends Product implements ShippableProducts {
    private double weight;
    private Date expireDate;

    public ShippableAndExpirableProduct(String name, double price, long quantity, double weight, Date expireDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expireDate = expireDate;
    }
    @Override
    public boolean isExpired(){
        return new Date().after(expireDate);
    }
    @Override
    public boolean isRequiresShipping(){
        return true;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
