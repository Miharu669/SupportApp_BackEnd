package dev.vero.supportApp.repositories;

import dev.vero.supportApp.models.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RequestRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    void whenFindById_thenReturnRequest() {

        Request request = new Request();
        request.setRequestName("John Doe");
        request.setSubject("Test Subject");
        request.setDescription("Test Description");
        request.setRequestDate(LocalDateTime.now());
        entityManager.persist(request);
        entityManager.flush();

        Request found = requestRepository.findById(request.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getRequestName()).isEqualTo(request.getRequestName());
        assertThat(found.getSubject()).isEqualTo(request.getSubject());
        assertThat(found.getDescription()).isEqualTo(request.getDescription());
    }

    @Test
    void whenFindAll_thenReturnRequestList() {

        Request request1 = new Request();
        request1.setRequestName("John Doe");
        request1.setSubject("Test Subject 1");
        request1.setDescription("Test Description 1");
        request1.setRequestDate(LocalDateTime.now());

        Request request2 = new Request();
        request2.setRequestName("Jane Doe");
        request2.setSubject("Test Subject 2");
        request2.setDescription("Test Description 2");
        request2.setRequestDate(LocalDateTime.now());

        entityManager.persist(request1);
        entityManager.persist(request2);
        entityManager.flush();

        List<Request> requests = requestRepository.findAll();

        assertThat(requests).hasSize(3);
    }

}