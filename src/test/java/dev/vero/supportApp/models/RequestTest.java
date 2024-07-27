package dev.vero.supportApp.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestTest {

    @Test
    public void testDefaultConstructor() {
        Request request = new Request();
        assertThat(request.getRequestDate()).isNotNull();
    }

    @Test
    public void testGettersAndSetters() {
        Request request = new Request();
        LocalDateTime now = LocalDateTime.now();
        request.setRequestName("John Doe");
        request.setSubject("Issue");
        request.setDescription("Test description");
        request.setRequestDate(now);

        assertThat(request.getRequestName()).isEqualTo("John Doe");
        assertThat(request.getSubject()).isEqualTo("Issue");
        assertThat(request.getDescription()).isEqualTo("Test description");
        assertThat(request.getRequestDate()).isEqualTo(now);
    }

    @Test
    public void testIdSetterGetter() {
        Request request = new Request();
        request.setId(1L);
        assertThat(request.getId()).isEqualTo(1L);
    }

    @Test
    public void testRequesterNameSetterGetter() {
        Request request = new Request();
        request.setRequestName("Jane Doe");
        assertThat(request.getRequestName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testSubjectSetterGetter() {
        Request request = new Request();
        request.setSubject("Bla bla");
        assertThat(request.getSubject()).isEqualTo("Bla bla");
    }

    @Test
    public void testDescriptionSetterGetter() {
        Request request = new Request();
        request.setDescription("Bla bla bla");
        assertThat(request.getDescription()).isEqualTo("Bla bla bla");
    }

    @Test
    public void testRequestDateSetterGetter() {
        Request request = new Request();
        LocalDateTime now = LocalDateTime.now();
        request.setRequestDate(now);
        assertThat(request.getRequestDate()).isEqualTo(now);
    }
}
