package id.ac.ui.cs.advprog.gametimekeranjang.service;

import id.ac.ui.cs.advprog.gametimekeranjang.model.Cart;
import id.ac.ui.cs.advprog.gametimekeranjang.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService() {
        this.cartRepository = CartRepository.getInstance();
    }

    // Additional constructor for testing
    public CartService(CartRepository repository) {
        this.cartRepository = repository;
    }

    public Cart addProduct(int productId, int quantity) {
        return cartRepository.addProduct(productId, quantity);
    }

    public List<Cart> findAllProducts() {
        Map<Integer, Cart> products = cartRepository.getAllProducts();
        return new ArrayList<>(products.values());
    }

    public Cart updateProduct(int productId, int quantity) {
        return cartRepository.updateProduct(productId, quantity);
    }

    public void deleteProduct(int productId) {
        cartRepository.removeProduct(productId);
    }

    public Cart findProductById(int productId) {
        return cartRepository.getProduct(productId);
    }
}
