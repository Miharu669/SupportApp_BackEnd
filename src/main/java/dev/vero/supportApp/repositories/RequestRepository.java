package dev.vero.supportApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import dev.vero.supportApp.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

    
} 
    

