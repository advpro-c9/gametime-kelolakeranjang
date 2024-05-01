package id.ac.ui.cs.advprog.gametimekeranjang.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/api/cart")
public class CartController {

    @GetMapping("/")
    public String createHomePage(){
        return "Cart";
    }

    @GetMapping("/add")
    public ResponseEntity<String> getAddProductForm() {
        return ResponseEntity.ok("This is a example POST to add a product.");
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