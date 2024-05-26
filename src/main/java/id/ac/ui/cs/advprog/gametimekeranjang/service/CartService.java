package id.ac.ui.cs.advprog.gametimekeranjang.service;


import id.ac.ui.cs.advprog.gametimekeranjang.repository.CartRepository;
import id.ac.ui.cs.advprog.gametimekeranjang.model.CartItems;
import id.ac.ui.cs.advprog.gametimekeranjang.config.GameApiProperties;
import id.ac.ui.cs.advprog.gametimekeranjang.dto.GameResponse;
import id.ac.ui.cs.advprog.gametimekeranjang.dto.KeranjangResponse;
import id.ac.ui.cs.advprog.gametimekeranjang.dto.WebResponse;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class CartService {

    private static final String EMAIL_NOT_FOUND_MESSAGE = "Cart not found.";
    private static final String ITEM_NOT_FOUND_MESSAGE = "Item not found in cart.";
    private final CartRepository cartRepository;
    private final Pricing discount;
    private final Pricing normalPrice;
    private final RestTemplate restTemplate;
    private final GameApiProperties gameApiProperties;
    private Pricing pricing;

    public CartService(CartRepository cartRepository,
                       @Qualifier("discount") Pricing discount,
                       @Qualifier("normalPrice") Pricing normalPrice,
                       RestTemplate restTemplate,
                       GameApiProperties gameApiProperties) {
        this.cartRepository = cartRepository;
        this.discount = discount;
        this.normalPrice = normalPrice;
        this.restTemplate = restTemplate;
        this.gameApiProperties = gameApiProperties;
        setPricing();
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    @Async
    public CompletableFuture<Void> applyDiscountsToAllCarts() {
        List<CartItems> carts = cartRepository.findAll();
        for (CartItems cartItems : carts) {
            Map<String, Double> itemPrices = getItemPrices();
            double totalPrice = discount.calculateTotal(cartItems.getItems(), itemPrices);
            cartItems.setTotalPrice(totalPrice);
            cartRepository.save(cartItems);
        }
        return CompletableFuture.completedFuture(null);
    }

    public KeranjangResponse addItem(String email, String itemId, int quantity) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null) {
            cartItems = new CartItems(email);
            cartItems = cartRepository.save(cartItems);
        }

        cartItems.getItems().merge(itemId, quantity, Integer::sum);

        Map<String, Double> itemPrices = getItemPrices();
        Map<String, Integer> itemQuantities = new HashMap<>();
        cartItems.getItems().forEach((key, value) -> itemQuantities.put(String.valueOf(key), value));

        double totalPrice = pricing.calculateTotal(itemQuantities, itemPrices);
        cartItems.setTotalPrice(totalPrice);

        cartItems = cartRepository.save(cartItems);

        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    public KeranjangResponse updateItem(String email, String itemId, int quantity) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null || !cartItems.getItems().containsKey(itemId)) {
            throw new IllegalArgumentException(ITEM_NOT_FOUND_MESSAGE);
        }

        cartItems.getItems().put(itemId, quantity);

        Map<String, Double> itemPrices = getItemPrices();
        double totalPrice = pricing.calculateTotal(cartItems.getItems(), itemPrices);
        cartItems.setTotalPrice(totalPrice);

        cartRepository.save(cartItems);
        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    public KeranjangResponse deleteItem(String email, String itemId) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null) {
            throw new IllegalArgumentException(EMAIL_NOT_FOUND_MESSAGE);
        }

        cartItems.getItems().remove(itemId);

        Map<String, Double> itemPrices = getItemPrices();
        double totalPrice = pricing.calculateTotal(cartItems.getItems(), itemPrices);
        cartItems.setTotalPrice(totalPrice);

        cartRepository.save(cartItems);
        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    public KeranjangResponse incrementItem(String email, String itemId) {
        return addItem(email, itemId, 1);
    }

    public KeranjangResponse decrementItem(String email, String itemId) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null || !cartItems.getItems().containsKey(itemId)) {
            throw new IllegalArgumentException(ITEM_NOT_FOUND_MESSAGE);
        }

        int currentQuantity = cartItems.getItems().get(itemId);
        if (currentQuantity <= 1) {
            cartItems.getItems().remove(itemId);
        } else {
            cartItems.getItems().put(itemId, currentQuantity - 1);
        }

        Map<String, Double> itemPrices = getItemPrices();
        double totalPrice = pricing.calculateTotal(cartItems.getItems(), itemPrices);
        cartItems.setTotalPrice(totalPrice);

        cartRepository.save(cartItems);
        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    public KeranjangResponse findCartByEmail(String email) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null) {
            throw new IllegalArgumentException(EMAIL_NOT_FOUND_MESSAGE);
        }

        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    public KeranjangResponse clearCart(String email) {
        CartItems cartItems = cartRepository.findByEmail(email);
        if (cartItems == null) {
            throw new IllegalArgumentException(EMAIL_NOT_FOUND_MESSAGE);
        }

        cartItems.getItems().clear();
        cartItems.setTotalPrice(0.0);

        cartRepository.save(cartItems);
        return new KeranjangResponse(cartItems.getEmail(), cartItems.getItems(), cartItems.getTotalPrice());
    }

    Map<String, Double> getItemPrices() {
        ResponseEntity<WebResponse<List<GameResponse>>> response = restTemplate.exchange(
                gameApiProperties.getUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<WebResponse<List<GameResponse>>>() {}
        );

        List<GameResponse> games = (response != null && response.getBody() != null && response.getBody().getData() != null)
                ? response.getBody().getData()
                : new ArrayList<>();
        Map<String, Double> itemPrices = new HashMap<>();
        for (GameResponse game : games) {
            itemPrices.put(game.getId(), game.getPrice().doubleValue());
        }
        return itemPrices;
    }

    private void setPricing() {
        // Some logic to set pricing
    }
}
