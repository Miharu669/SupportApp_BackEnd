// package dev.vero.suportApp.services;

// import dev.vero.suportApp.models.Request;
// import dev.vero.suportApp.repositories.RequestRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;


// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.*;

// public class RequestServiceTest {

//     @Mock
//     private RequestRepository requestRepository;

//     @InjectMocks
//     private RequestService requestService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testGetAllRequests() {
//         Request request = new Request();
//         request.setRequesterName("John Doe");
//         request.setTopic("Issue with Service");
//         request.setDescription("Detailed issue description.");
//         request.setRequestDate(LocalDateTime.now());

//         when(requestRepository.findAll()).thenReturn(List.of(request));

//         List<Request> requests = requestService.getAll();

//         assertThat(requests).isNotEmpty();
//         assertThat(requests.get(0).getRequesterName()).isEqualTo("John Doe");
//     }

//     @Test
//     void testFindById() {
//         Request request = new Request();
//         request.setRequesterName("John Doe");
//         request.setTopic("Issue with Service");
//         request.setDescription("Detailed issue description.");
//         request.setRequestDate(LocalDateTime.now());

//         when(requestRepository.findById(anyLong())).thenReturn(Optional.of(request));

//         Optional<Request> foundRequest = requestService.findById(1L);

//         assertThat(foundRequest).isPresent();
//         assertThat(foundRequest.get().getRequesterName()).isEqualTo("John Doe");
//     }

//     @Test
//     void testStore() {
//         Request request = new Request();
//         request.setRequesterName("John Doe");
//         request.setTopic("Issue with Service");
//         request.setDescription("Detailed issue description.");
//         request.setRequestDate(LocalDateTime.now());

//         when(requestRepository.save(any(Request.class))).thenReturn(request);

//         Request createdRequest = requestService.store(request);

//         assertThat(createdRequest).isNotNull();
//         assertThat(createdRequest.getRequesterName()).isEqualTo("John Doe");
//     }

//     @Test
//     void testUpdate() {

//         Request existingRequest = new Request();
//         existingRequest.setId(1L);
//         existingRequest.setRequesterName("John Doe");
//         existingRequest.setTopic("Issue with Service");
//         existingRequest.setDescription("Detailed issue description.");
//         existingRequest.setRequestDate(LocalDateTime.now());

//         Request updatedRequest = new Request();
//         updatedRequest.setRequesterName("Jane Doe");
//         updatedRequest.setTopic("Service Issue Updated");
//         updatedRequest.setDescription("Updated issue description.");

//         when(requestRepository.findById(1L)).thenReturn(Optional.of(existingRequest));

//         existingRequest.setRequesterName(updatedRequest.getRequesterName());
//         existingRequest.setTopic(updatedRequest.getTopic());
//         existingRequest.setDescription(updatedRequest.getDescription());

//         when(requestRepository.save(any(Request.class))).thenReturn(existingRequest);

//         Request result = requestService.update(1L, updatedRequest);

//         assertThat(result).isNotNull();
//         assertThat(result.getRequesterName()).isEqualTo("Jane Doe");
//         assertThat(result.getTopic()).isEqualTo("Service Issue Updated");
//         assertThat(result.getDescription()).isEqualTo("Updated issue description.");
//         assertThat(result.getId()).isEqualTo(1L);

//         verify(requestRepository, times(1)).findById(1L);
//         verify(requestRepository, times(1)).save(existingRequest);
//     }

//     @Test
//     void testUpdateRequestNotFound() {
//         Request updatedRequest = new Request();
//         updatedRequest.setRequesterName("Jane Doe");
//         updatedRequest.setTopic("Service Issue Updated");
//         updatedRequest.setDescription("Updated issue description.");

//         when(requestRepository.findById(anyLong())).thenReturn(Optional.empty());

//         RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
//             requestService.update(1L, updatedRequest);
//         });

//         assertThat(thrownException).isNotNull();
//         assertThat(thrownException.getMessage()).isEqualTo("Request not found with id 1");
//     }

//     @Test
//     void testDelete() {
//         when(requestRepository.existsById(anyLong())).thenReturn(true);
//         doNothing().when(requestRepository).deleteById(anyLong());

//         requestService.delete(1L);

//         verify(requestRepository, times(1)).deleteById(anyLong());
//     }

//     @Test
//     void testDeleteRequestNotFound() {
//         when(requestRepository.existsById(anyLong())).thenReturn(false);

//         RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
//             requestService.delete(1L);
//         });

//         assertThat(thrownException).isNotNull();
//         assertThat(thrownException.getMessage()).isEqualTo("Request not found with id 1");
//     }
// }
