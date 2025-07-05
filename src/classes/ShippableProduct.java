package classes;

public class ShippableProduct extends Product implements ShippableProducts {
    private double weight;

    public ShippableProduct(String name, double price, long quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    @Override
    public boolean isRequiresShipping(){
        return true;
    }
}
