package id.ac.ui.cs.advprog.gametimekeranjang.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponse {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String category;
    private Integer stock;
}