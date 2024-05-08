package id.ac.ui.cs.advprog.gametimekeranjang.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import id.ac.ui.cs.advprog.gametimekeranjang.model.Stock;

/**
 *
 * @author ASUS
 */
@FeignClient(name = "product-data-client", url = "http://pembelianservice.com")
public interface stockDataClient{
    @GetMapping("/product{productId}")
    Stock getStockById(@PathVariable("productId") int stockId);
}

