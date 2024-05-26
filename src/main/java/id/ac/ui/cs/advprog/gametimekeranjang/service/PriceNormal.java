package id.ac.ui.cs.advprog.gametimekeranjang.service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("pricingNormal")
public class PriceNormal implements Pricing {
    @Override
    public double calculateTotal(Map<String, Integer> productQuantities, Map<String, Double> productPrices) {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            double price = productPrices.getOrDefault(entry.getKey(), 0.0);
            total += price * entry.getValue();
        }
        return total;
    }
}