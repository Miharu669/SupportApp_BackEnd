package dev.vero.supportApp;

import dev.vero.supportApp.models.Request;
import dev.vero.supportApp.repositories.RequestRepository;
import dev.vero.supportApp.services.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class SupportAppApplicationTests {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestService requestService;

    @Autowired
    private Environment environment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        // Test to ensure that the Spring application context loads
    }

    @Test
    void testRequestServiceBeanPresence() {
        assertThat(requestService).isNotNull();
    }

    @Test
    void testActiveProfiles() {
        assertThat(environment.getActiveProfiles()).contains("test");
    }

    @Test
    void testRequestRepositoryBeanPresence() {
        assertThat(requestRepository).isNotNull();
    }

    @Test
    void testUpdateRequestNotFound() {
        Request updatedRequest = new Request();
        updatedRequest.setRequestName("Jane Doe");
        updatedRequest.setSubject("Service Issue Updated");
        updatedRequest.setDescription("Updated issue description.");

        when(requestRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            requestService.update(1L, updatedRequest);
        });

        assertThat(thrownException).isNotNull();
        assertThat(thrownException.getMessage()).isEqualTo("Request not found with id 1");
    }

    @Test
    void testDeleteRequestNotFound() {
        when(requestRepository.existsById(anyLong())).thenReturn(false);

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            requestService.delete(1L);
        });

        assertThat(thrownException).isNotNull();
        assertThat(thrownException.getMessage()).isEqualTo("Request not found with id 1");
    }
}
