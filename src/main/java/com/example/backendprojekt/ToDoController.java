package com.example.backendprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public List<ToDoRecord> getTodoList() {
        return toDoService.getAllToDoItems();
    }

    @PostMapping
    public void addTodoItem(@RequestBody ToDoRecord todoRecord) {
        toDoService.addToDoItem(todoRecord);
    }

    @DeleteMapping("/{id}")
    public void removeTodoItem(@PathVariable String id) {
        toDoService.removeToDoItem(id);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> removeToDoItem(@PathVariable("id") UUID id) {
//        toDoService.removeToDoItem(id);
//        return ResponseEntity.ok("ToDo item with ID " + id + " removed successfully.");
//    }
    @PutMapping("/{id}")
    public void updateTodoItem(@PathVariable String id, @RequestBody ToDoRecord updatedToDo) {
        toDoService.updateToDoItem(id, updatedToDo);
    }

    @GetMapping("/{id}")
    public ToDoRecord getToDoItemById(@PathVariable String id) {
        return toDoService.getToDoItemById(id);
    }
}
