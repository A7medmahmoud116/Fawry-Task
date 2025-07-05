package classes;

import java.util.Date;

public class ExpirableProduct extends Product{
    private Date expireDate;

    public ExpirableProduct(String name, double price, long quantity, Date expireDate) {
        super(name, price, quantity);
        this.expireDate = expireDate;
    }
    @Override
    public boolean isExpired(){
        return new Date().after(expireDate);
    }
}
