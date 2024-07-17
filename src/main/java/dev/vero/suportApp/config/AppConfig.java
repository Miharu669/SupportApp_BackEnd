package dev.vero.suportApp.config;

import dev.vero.suportApp.repositories.RequestRepository;
import dev.vero.suportApp.services.RequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.vero.suportApp.repositories") // Fixed package name to match your actual package structure
public class AppConfig {

    @Bean
    public RequestService requestService(RequestRepository requestRepository) {
        return new RequestService(requestRepository);
    }
}
