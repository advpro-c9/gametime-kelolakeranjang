package id.ac.ui.cs.advprog.gametimekeranjang.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stock {
    private int productId;
    private String productName;
    private int quantity;
    private int price;

    public Stock(int productId, int quantity, int price, String productName) {
        this.productId = productId;
        this.quantity = quantity;
        this.price =price;
        this.productName = productName;
    }

}
