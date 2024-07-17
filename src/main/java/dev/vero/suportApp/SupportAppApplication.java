package dev.vero.suportApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.vero.supportApp")
public class SupportAppApplication {
    public static void main(String[] args) {
		SpringApplication.run(SupportAppApplication.class, args);
	}

}
