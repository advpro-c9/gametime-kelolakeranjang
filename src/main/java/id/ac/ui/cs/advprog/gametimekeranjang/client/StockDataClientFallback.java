package id.ac.ui.cs.advprog.gametimekeranjang.client;
import org.springframework.stereotype.Component;

import id.ac.ui.cs.advprog.gametimekeranjang.model.Stock;

@Component
public class StockDataClientFallback implements stockDataClient {
    public Stock getStockById(int stockId) {
        // Kembali ke dummy data saat service eksternal tidak tersedia
        return new Stock(1, 32,  5000, "abcd");
    }
}