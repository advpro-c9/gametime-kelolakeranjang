package id.ac.ui.cs.advprog.gametimekeranjang.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class CartItems {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @ElementCollection
    @CollectionTable(name = "cart_items_details", joinColumns = @JoinColumn(name = "cart_items_email"))
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<String, Integer> items = new HashMap<>();

    @Column(name = "total_price")
    private double totalPrice;

    // Default constructor
    public CartItems() {
        this.items = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public CartItems(String email) {
        this.email = email;
        this.items = new HashMap<>();
        this.totalPrice = 0.0;
    }
}
