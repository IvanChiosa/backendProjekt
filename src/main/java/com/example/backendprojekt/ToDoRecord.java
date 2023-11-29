package com.example.backendprojekt;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ToDo")
@With
public record ToDoRecord(String id, String description, String status) {
}
