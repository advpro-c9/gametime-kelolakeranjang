package id.ac.ui.cs.advprog.gametimekeranjang.service;

import java.util.Map;

public interface Pricing {
    double calculateTotal(Map<String, Integer> productQuantities, Map<String, Double> productPrices);
}