package dev.vero.suportApp.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import dev.vero.suportApp.services.SupportAppService;
import dev.vero.suportApp.models.SupportApp;


@RestController
public class SupportAppController {

    private final SupportAppService service;

    @Autowired
    public SupportAppController(SupportAppService service) {
        this.service = service;
    }

    @GetMapping("/supportapps")
    public List<SupportApp> index() {
        return service.getAll();
    }
}

