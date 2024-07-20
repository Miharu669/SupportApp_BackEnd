// package dev.vero.suportApp.models;

// import org.junit.jupiter.api.Test;
// import java.time.LocalDateTime;
// import static org.assertj.core.api.Assertions.assertThat;

// public class RequestTest {

//     @Test
//     public void testDefaultConstructor() {
//         Request request = new Request();
//         assertThat(request.getRequestDate()).isNotNull();
//     }

//     @Test
//     public void testGettersAndSetters() {
//         Request request = new Request();
//         LocalDateTime now = LocalDateTime.now();
//         request.setRequesterName("John Doe");
//         request.setTopic("Issue");
//         request.setDescription("Test description");
//         request.setRequestDate(now);

//         assertThat(request.getRequesterName()).isEqualTo("John Doe");
//         assertThat(request.getTopic()).isEqualTo("Issue");
//         assertThat(request.getDescription()).isEqualTo("Test description");
//         assertThat(request.getRequestDate()).isEqualTo(now);
//     }

//     @Test
//     public void testIdSetterGetter() {
//         Request request = new Request();
//         request.setId(1L);
//         assertThat(request.getId()).isEqualTo(1L);
//     }

//     @Test
//     public void testRequesterNameSetterGetter() {
//         Request request = new Request();
//         request.setRequesterName("Jane Doe");
//         assertThat(request.getRequesterName()).isEqualTo("Jane Doe");
//     }

//     @Test
//     public void testTopicSetterGetter() {
//         Request request = new Request();
//         request.setTopic("Feature Request");
//         assertThat(request.getTopic()).isEqualTo("Feature Request");
//     }

//     @Test
//     public void testDescriptionSetterGetter() {
//         Request request = new Request();
//         request.setDescription("Add new feature");
//         assertThat(request.getDescription()).isEqualTo("Add new feature");
//     }

//     @Test
//     public void testRequestDateSetterGetter() {
//         Request request = new Request();
//         LocalDateTime now = LocalDateTime.now();
//         request.setRequestDate(now);
//         assertThat(request.getRequestDate()).isEqualTo(now);
//     }
// }
