import classes.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void ship(List<CartItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof ShippableProducts) {
                double weight = ((ShippableProducts) product).getWeight() * item.getQuantity();
                System.out.printf("%dx %-12s %5.0fg%n", item.getQuantity(), product.getName(), weight);
                totalWeight += weight;
            }
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);
    }
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("cart is empty");
            return;
        }
        double subTotal = 0.0;
        double shipping = 0.0;
        List<CartItem> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                System.out.println("product "+ product.getName()+" is expired");
                return;
            }

            if (product.getQuantity() < item.getQuantity()) {
                System.out.println("product "+ product.getName()+" not enough ... out of stock");
                return;
            }

            product.setQuantity( product.getQuantity() - item.getQuantity() );
            subTotal += item.getTotalPrice();

            if (product.isRequiresShipping()) {
                shippableItems.add(item);
                shipping += 15.0;
            }
        }

        double total = subTotal + shipping;

        boolean successful = customer.deduct(total);
        if(! successful)
            return;

        if (!shippableItems.isEmpty()) {
            ship(shippableItems);
        }

        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getCartItems()) {
            System.out.printf("%sx %-12s %5.0f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getTotalPrice()
            );        }
        System.out.println("--------------------------");
        System.out.printf("%-16s %5.0f%n", "Subtotal", subTotal);
        System.out.printf("%-16s %5.0f%n", "Shipping", shipping);
        System.out.printf("%-16s %5.0f%n", "Amount", total);
    }
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        Date expireDate = calendar.getTime();

        ShippableAndExpirableProduct cheese = new ShippableAndExpirableProduct(
                "Cheese", 100, 5, 200, expireDate
        );
        ShippableAndExpirableProduct biscuits = new ShippableAndExpirableProduct(
                "Biscuits", 150, 17, 700 , expireDate
        );
        NonShippableAndExpirableProduct tv = new NonShippableAndExpirableProduct(
                "TV", 2000,10
        );
        NonShippableAndExpirableProduct scratchCard = new NonShippableAndExpirableProduct(
                "Scratch Card", 1500, 0
        );

        Customer customer = new Customer("Ahmed Mahmoud",1000);

        // example 1
        System.out.println("********** Example 1 **********");
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits,1);
        checkout(customer, cart);
        // example 2 --> out of stock
        System.out.println("********** Example 2 **********");
        Cart cart2 = new Cart();
        cart2.add(scratchCard, 2);
        cart2.add(biscuits,1);
        checkout(customer, cart2);

        // example 3 --> low balance
        System.out.println("********** Example 3 **********");
        Cart cart3 = new Cart();
        cart3.add(tv, 2);
        checkout(customer, cart3);


    }
}