package com.example.backendprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepo toDoRepository;

    public List<ToDoRecord> getAllToDoItems() {
        return toDoRepository.findAll();
    }

    public void addToDoItem(ToDoRecord todoRecord) {
        toDoRepository.save(todoRecord);
    }

    public void removeToDoItem(String id) {
        toDoRepository.deleteById(id);
    }

    public void updateToDoItem(String id, ToDoRecord updatedToDo) {
        Optional<ToDoRecord> existingToDo = toDoRepository.findById(id);
        existingToDo.ifPresent(todoToUpdate -> {
            todoToUpdate.withDescription(updatedToDo.description());
            todoToUpdate.withStatus(updatedToDo.status());
            toDoRepository.save(todoToUpdate);
        });
    }

    public ToDoRecord getToDoItemById(String id) {
        Optional<ToDoRecord> toDoOptional = toDoRepository.findById(id);

        // Überprüfen, ob das ToDo-Element vorhanden ist
        return toDoOptional.orElse(null);
    }


}