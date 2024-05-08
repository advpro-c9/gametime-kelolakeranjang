package id.ac.ui.cs.advprog.gametimekeranjang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GametimeKeranjangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametimeKeranjangApplication.class, args);
    }

}
