package id.ac.ui.cs.advprog.gametimekeranjang.repository;

import id.ac.ui.cs.advprog.gametimekeranjang.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepository {
    private static CartRepository instance;
    private Map<Integer, Cart> cartProducts = new HashMap<>();

    // Singleton pattern
    private CartRepository() {}

    // Singleton global access
    public static synchronized CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    public Cart addProduct(int productId, int quantity) {
        if (cartProducts.containsKey(productId)) {
            Cart existingItem = cartProducts.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return existingItem;
        } else {
            Cart newItem = new Cart(productId, quantity);
            cartProducts.put(productId, newItem);
            return newItem;
        }
    }

    public Cart getProduct(int productId) {
        return cartProducts.get(productId);
    }

    public Cart updateProduct(int productId, int quantity) {
        if (cartProducts.containsKey(productId)) {
            Cart item = cartProducts.get(productId);
            item.setQuantity(quantity);
            return item;
        }
        return null;
    }

    public void removeProduct(int productId) {
        cartProducts.remove(productId);
    }

    public Map<Integer, Cart> getAllProducts() {
        return new HashMap<>(cartProducts);
    }

    public static synchronized void resetInstance() {
        instance = null;
    }
}
