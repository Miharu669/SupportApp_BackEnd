package dev.vero.supportApp.controllers;

import dev.vero.supportApp.models.Request;
import dev.vero.supportApp.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-requests")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    /**
     * Retrieve all support requests.
     *
     * @return List of all Request objects
     */
    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = service.getAll();
        return ResponseEntity.ok(requests);
    }

    /**
     * Retrieve a specific support request by its ID.
     *
     * @param id The ID of the request
     * @return ResponseEntity containing the Request if found, or a not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new support request.
     *
     * @param newRequest The Request object to be created
     * @return ResponseEntity containing the created Request and CREATED status
     */
    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody Request newRequest) {
        try {
            Request createdRequest = service.store(newRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
        } catch (Exception e) {
            e.printStackTrace(); // This will print the stack trace to your server logs
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating request: " + e.getMessage());
        }
    }

    /**
     * Update an existing support request.
     *
     * @param id The ID of the request to update
     * @param updatedRequest The updated Request object
     * @return ResponseEntity containing the updated Request if found, or a not found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        try {
            Request updated = service.update(id, updatedRequest);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a support request.
     *
     * @param id The ID of the request to delete
     * @return ResponseEntity with no content if successful, or a not found status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Test endpoint to check if the API is working.
     *
     * @return A string indicating the API is working
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is working");
    }
}