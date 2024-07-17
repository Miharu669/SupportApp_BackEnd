package dev.vero.suportApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import dev.vero.suportApp.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

    
} 
    

