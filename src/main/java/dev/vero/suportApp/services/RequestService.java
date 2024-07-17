package dev.vero.suportApp.services;

import org.springframework.stereotype.Service;
import dev.vero.suportApp.models.Request;
import dev.vero.suportApp.repositories.RequestRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    public Request store(@Valid Request request) {
if (request.getRequesterName() == null || request.getRequesterName().isBlank()) {
            throw new IllegalArgumentException("Requester name is required");
        }
        return requestRepository.save(request);
    }

    public Request update(Long id, @Valid Request request) {
        if (requestRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Request not found with id " + id);
        }
        request.setId(id);
        return requestRepository.save(request);
    }


    public void delete(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new RuntimeException("Request not found with id " + id);
        }
        requestRepository.deleteById(id);
    }
}
