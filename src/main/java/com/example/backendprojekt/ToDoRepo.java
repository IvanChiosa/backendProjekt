package com.example.backendprojekt;

import lombok.With;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ToDoRepo extends MongoRepository<ToDoRecord, String> {
}
