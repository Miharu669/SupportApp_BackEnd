package dev.vero.suportApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import dev.vero.suportApp.services.RequestService;
import dev.vero.suportApp.models.Request;


@RestController
@RequestMapping("/api/support-requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Request request = service.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
        return ResponseEntity.ok(request);
    }

    @PostMapping("/add")
    public ResponseEntity<Request> createRequest(@RequestBody Request newRequest) {
        Request createdRequest = service.store(newRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        Request updated = service.update(id, updatedRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
