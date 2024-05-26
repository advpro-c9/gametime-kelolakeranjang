package id.ac.ui.cs.advprog.gametimekeranjang.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeranjangResponse {
    private String email;
    private Map<String, Integer> items;
    private double totalPrice;
}