package classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public void add(Product product, long quantity){
        if(quantity <= 0){
            System.out.println("enter a valid quantity");
            return;
        }
        if(quantity > product.getQuantity()){
            System.out.println("this quantity out of stock for the "+product.getName());
            return;
        }
        cartItems.add(new CartItem(product,quantity));
    }
}
