package id.ac.ui.cs.advprog.gametimekeranjang.model;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Cart {
    private int productId;
    private int quantity;

    public Cart(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
