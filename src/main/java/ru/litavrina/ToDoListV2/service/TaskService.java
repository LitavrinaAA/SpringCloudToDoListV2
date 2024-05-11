package ru.litavrina.ToDoListV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.litavrina.ToDoListV2.model.Task;
import ru.litavrina.ToDoListV2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
    public Optional<Task> getTaskById(Long id) {

        return taskRepository.findById(id);
//        return taskRepository.findById(id).orElse(null);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
