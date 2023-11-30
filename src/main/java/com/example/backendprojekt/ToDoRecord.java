package com.example.backendprojekt;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ToDoList")
@With
public record ToDoRecord(String id, String description, String status) {
}
