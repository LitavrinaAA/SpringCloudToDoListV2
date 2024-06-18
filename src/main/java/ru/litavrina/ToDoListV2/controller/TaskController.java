package ru.litavrina.ToDoListV2.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.litavrina.ToDoListV2.aspect.TrackUserAction;
import ru.litavrina.ToDoListV2.model.Task;
import ru.litavrina.ToDoListV2.service.FileGateway;
import ru.litavrina.ToDoListV2.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apitasks")
public class TaskController {

    private static final Logger logger = LogManager.getRootLogger();
    private final TaskService taskService;
    //для интеграции
    private final FileGateway fileGateway;

    //Просто возвращает все таски из БД
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        logger.info("запросили все таски");
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.FOUND);
    }

    //Возвращает одну таску из БД в Optional и проверяет isPresent
    @GetMapping("{id}")
    public ResponseEntity<Task> getTasksByID(@PathVariable Long id) {
        logger.info("кто-то просит таскy");
        if (taskService.getTaskById(id).isPresent()) {
            return new ResponseEntity<>(taskService.getTaskById(id).get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        Но можно проще, если нужно:
//        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.FOUND);
    }


    //Просто создаем таску в БД
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("создали таску");
        fileGateway.writeToFile(task.getTitle()+ ".txt", task.getDescription());
        return new ResponseEntity<>(taskService.saveTask(task) , HttpStatus.CREATED);
    }

    //просто сохраняем таску, но присваеваем ей Id из пути
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task task) {
        task.setId(id);
        return new ResponseEntity<>(taskService.saveTask(task), HttpStatus.OK);
    }

    //Просто удляем по Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

}
