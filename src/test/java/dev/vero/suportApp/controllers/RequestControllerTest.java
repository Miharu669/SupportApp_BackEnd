package dev.vero.suportApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vero.suportApp.models.Request;
import dev.vero.suportApp.services.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService service;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new RequestController(service)).build();
    }

    @Test
    @DisplayName("Should return a list of all requests")
    void testGetAllRequests() throws Exception {
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Alice");
        request1.setTopic("Issue with login");
        request1.setDescription("Unable to login to the system.");
        request1.setRequestDate(LocalDateTime.now());

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Bob");
        request2.setTopic("Bug in dashboard");
        request2.setDescription("Dashboard displays incorrect data.");
        request2.setRequestDate(LocalDateTime.now());

        List<Request> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);

        when(service.getAll()).thenReturn(requests);

        mockMvc.perform(get("/api/support-requests/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].requesterName").value("Alice"))
                .andExpect(jsonPath("$[0].topic").value("Issue with login"))
                .andExpect(jsonPath("$[0].description").value("Unable to login to the system."))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].requesterName").value("Bob"))
                .andExpect(jsonPath("$[1].topic").value("Bug in dashboard"))
                .andExpect(jsonPath("$[1].description").value("Dashboard displays incorrect data."));
    }

    @Test
    @DisplayName("Should return a request by ID")
    void testGetRequestById() throws Exception {
        Request request = new Request();
        request.setId(1L);
        request.setRequesterName("Alice");
        request.setTopic("Issue with login");
        request.setDescription("Unable to login to the system.");
        request.setRequestDate(LocalDateTime.now());

        when(service.findById(1L)).thenReturn(Optional.of(request));

        mockMvc.perform(get("/api/support-requests/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.requesterName").value("Alice"))
                .andExpect(jsonPath("$.topic").value("Issue with login"))
                .andExpect(jsonPath("$.description").value("Unable to login to the system."));
    }

    @Test
    @DisplayName("Should create a new request")
    void testCreateRequest() throws Exception {
        Request newRequest = new Request();
        newRequest.setRequesterName("Alice");
        newRequest.setTopic("Issue with login");
        newRequest.setDescription("Unable to login to the system.");

        Request createdRequest = new Request();
        createdRequest.setId(1L);
        createdRequest.setRequesterName("Alice");
        createdRequest.setTopic("Issue with login");
        createdRequest.setDescription("Unable to login to the system.");
        createdRequest.setRequestDate(LocalDateTime.now());

        when(service.store(any(Request.class))).thenReturn(createdRequest);

        String json = mapper.writeValueAsString(newRequest);

        mockMvc.perform(post("/api/support-requests/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.requesterName").value("Alice"))
                .andExpect(jsonPath("$.topic").value("Issue with login"))
                .andExpect(jsonPath("$.description").value("Unable to login to the system."));
    }

    @Test
    @DisplayName("Should update an existing request")
    void testUpdateRequest() throws Exception {
        Request updatedRequest = new Request();
        updatedRequest.setId(1L);
        updatedRequest.setRequesterName("Alice");
        updatedRequest.setTopic("Issue with login");
        updatedRequest.setDescription("Updated description");
        updatedRequest.setRequestDate(LocalDateTime.now());

        when(service.update(anyLong(), any(Request.class))).thenReturn(updatedRequest);

        String json = mapper.writeValueAsString(updatedRequest);

        mockMvc.perform(put("/api/support-requests/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.requesterName").value("Alice"))
                .andExpect(jsonPath("$.topic").value("Issue with login"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    @DisplayName("Should delete a request")
    void testDeleteRequest() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/support-requests/delete/1"))
                .andExpect(status().isNoContent());
    }

}
