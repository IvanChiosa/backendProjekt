package com.example.backendprojekt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @InjectMocks
    private ToDoService toDoService;
    @Mock
    private ToDoRepo toDoRepository;

    @Test
    public void testAddToDoItem() {
        // Erstelle ein Beispiel ToDoRecord
        ToDoRecord todoRecord = new ToDoRecord("1", "Example Description", "Open");

        // Setze das Verhalten des Mock-Repositories
        when(toDoRepository.save(any(ToDoRecord.class))).thenReturn(todoRecord);

        // Rufe die Methode auf, die getestet werden soll
        toDoService.addToDoItem(todoRecord);

        // Überprüfe, ob die Methode im Repository aufgerufen wurde
        verify(toDoRepository, times(1)).save(todoRecord);
    }

    @Test
    public void testUpdateToDoItem() {
        // Erstelle ein Beispiel ToDoRecord
        ToDoRecord existingToDo = new ToDoRecord("1", "Description", "Open");
        ToDoRecord updatedToDo = new ToDoRecord("1", "Updated Description", "Done");

        // Setze das Verhalten des Mock-Repositories
        when(toDoRepository.findById("1")).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.save(any(ToDoRecord.class))).thenReturn(updatedToDo);

        // Rufe die Methode auf, die getestet werden soll
        toDoService.updateToDoItem("1", updatedToDo);

        // Überprüfe, ob die Methode im Repository aufgerufen wurde
        verify(toDoRepository, times(1)).findById("1");
        verify(toDoRepository, times(1)).save(any(ToDoRecord.class));
    }

    @Test
    public void testGetToDoItemById() {
        // Erstelle ein Beispiel ToDoRecord
        ToDoRecord existingToDo = new ToDoRecord("1", "Description", "Open");

        // Setze das Verhalten des Mock-Repositories
        when(toDoRepository.findById("1")).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.findById("2")).thenReturn(Optional.empty());

        // Teste für vorhandenes ToDo-Element
        ToDoRecord resultToDo = toDoService.getToDoItemById("1");
        assertEquals(existingToDo, resultToDo);

        // Teste für nicht vorhandenes ToDo-Element
        resultToDo = toDoService.getToDoItemById("2");
        assertNull(resultToDo);

        // Überprüfe, ob die Methode im Repository aufgerufen wurde
        verify(toDoRepository, times(1)).findById("1");
        verify(toDoRepository, times(1)).findById("2");
    }


//    @Test
//    public void testRemoveToDoItem() {
//        // Erstelle ein Beispiel ToDoRecord
//        ToDoRecord existingToDo = new ToDoRecord("1", "Description", "Open");
//
//        // Setze das Verhalten des Mock-Repositories
//        when(toDoRepository.findById("1")).thenReturn(Optional.of(existingToDo));
//
//        // Rufe die Methode auf, die getestet werden soll
//        toDoService.removeToDoItem("1");
//
//        // Überprüfe, ob die Methode im Repository aufgerufen wurde
//        verify(toDoRepository, times(1)).findById("1");
//        verify(toDoRepository, times(1)).deleteById("1");
//    }
}