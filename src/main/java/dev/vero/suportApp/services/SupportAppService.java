package dev.vero.suportApp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import dev.vero.suportApp.models.SupportApp;
import dev.vero.suportApp.repositories.SupportAppRepository;;

@Service
public class SupportAppService {

    private final SupportAppRepository repository;

    @Autowired
    public SupportAppService(SupportAppRepository repository) {
        this.repository = repository;
    }

    public List<SupportApp> getAll() {
        return repository.findAll();
    }
}
