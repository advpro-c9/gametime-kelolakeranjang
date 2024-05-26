package id.ac.ui.cs.advprog.gametimekeranjang.controller;


import org.springframework.http.ResponseEntity;
import id.ac.ui.cs.advprog.gametimekeranjang.service.CartService;
import id.ac.ui.cs.advprog.gametimekeranjang.dto.KeranjangResponse;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.gametimekeranjang.repository.CartRepository;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Shopping Cart API");
    }

    @PostMapping("/add")
    public ResponseEntity<KeranjangResponse> addItem(@RequestParam String email, @RequestParam String itemId, @RequestParam int quantity) {
        try {
            KeranjangResponse response = cartService.addItem(email, itemId, quantity);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/increment")
    public ResponseEntity<KeranjangResponse> incrementItem(@RequestParam String email, @RequestParam String itemId) {
        KeranjangResponse response = cartService.incrementItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrement")
    public ResponseEntity<KeranjangResponse> decrementItem(@RequestParam String email, @RequestParam String itemId) {
        KeranjangResponse response = cartService.decrementItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<KeranjangResponse> removeItem(@RequestParam String email, @RequestParam String itemId) {
        KeranjangResponse response = cartService.deleteItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<KeranjangResponse> updateItem(@RequestParam String email, @RequestParam String itemId, @RequestParam int quantity) {
        KeranjangResponse response = cartService.updateItem(email, itemId, quantity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/{email}")
    public ResponseEntity<KeranjangResponse> viewCart(@PathVariable String email) {
        KeranjangResponse response = cartService.findCartByEmail(email);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear/{email}")
    public ResponseEntity<KeranjangResponse> clearCart(@PathVariable String email) {
        KeranjangResponse response = cartService.clearCart(email);
        return ResponseEntity.ok(response);
    }
}