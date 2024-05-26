package id.ac.ui.cs.advprog.gametimekeranjang;

import id.ac.ui.cs.advprog.gametimekeranjang.config.GameApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(GameApiProperties.class)
public class GametimeKeranjangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GametimeKeranjangApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}