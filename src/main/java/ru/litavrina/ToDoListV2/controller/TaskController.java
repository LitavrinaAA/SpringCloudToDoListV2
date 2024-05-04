package ru.litavrina.ToDoListV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.litavrina.ToDoListV2.model.Task;
import ru.litavrina.ToDoListV2.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apitasks")
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println("кто-то просит все таски");
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Task> getTasksByID(@PathVariable Long id) {
        System.out.println("кто-то просит таскy");
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        return new ResponseEntity<>(taskService.saveTask(task), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                           @RequestBody Task task) {
        task.setId(id);
        return new ResponseEntity<>(taskService.saveTask(task), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

}
