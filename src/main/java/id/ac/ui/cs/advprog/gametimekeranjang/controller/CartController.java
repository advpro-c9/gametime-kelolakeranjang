package id.ac.ui.cs.advprog.gametimekeranjang.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ui.cs.advprog.gametimekeranjang.model.Cart;
import id.ac.ui.cs.advprog.gametimekeranjang.repository.CartRepository;


@RestController
public class CartController {

    private final CartRepository repo;

    CartController(CartRepository repo){
        this.repo =repo;
    }

    @GetMapping("")
    public String createHomePage(){
        return "Cart";
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> getAddProductForm(@RequestBody Cart cart) {

        return ResponseEntity.ok(repo.addProduct(cart.getProductId(), cart.getQuantity()));
    }

    @GetMapping("/remove")
    public ResponseEntity<String> getRemoveProductForm() {
        return ResponseEntity.ok("This is a example POST to remove a Product.");
    }

    @GetMapping("/update")
    public ResponseEntity<String> getUpdateProductForm() {
        return ResponseEntity.ok("This is a example POST to update a Product.");
    }

}