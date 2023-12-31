package com.example.backendprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public void updateToDoItem(String id, ToDoRecord updatedToDo) {
        Optional<ToDoRecord> existingToDo = toDoRepository.findById(id);
        existingToDo.ifPresent(todoToUpdate -> {
            ToDoRecord updatedRecord = new ToDoRecord(
                    todoToUpdate.id(),
                    todoToUpdate.description(),
                    updatedToDo.status()
            );
            toDoRepository.save(updatedRecord);
        });
    }

    public ToDoRecord getToDoItemById(String id) {
        Optional<ToDoRecord> toDoOptional = toDoRepository.findById(id);

        // Überprüfen, ob das ToDo-Element vorhanden ist
        return toDoOptional.orElse(null);
    }

    public void removeToDoItem(String id) {
        toDoRepository.deleteById(id);
    }

//    public void removeToDoItem(UUID id) {
//        toDoRepository.deleteById(id.toString());
//    }
}