package ru.litavrina.ToDoListV2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.litavrina.ToDoListV2.exeption.TaskNotFoundException;
import ru.litavrina.ToDoListV2.model.Task;
import ru.litavrina.ToDoListV2.repository.TaskRepository;
import ru.litavrina.ToDoListV2.service.TaskService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitTests {
    //создать экземпляр, обернул проксиобъектом бин сервис
    @InjectMocks
    private TaskService taskService;
    // это заглушка
    @Mock
    private TaskRepository taskRepository;


    //задача для тестирования
    private static Task task;
    // создается тестовая задача перед каждым тестом
    @BeforeEach
    public void setParameters() {
        task = new Task();
        task.setId(1L);
        task.setTitle("тренировка");
        task.setDescription("записаться на тренировку по боксу к Наташе. купить перчатки.");
    }

    @Test
    public void  savingTest(){
        taskService.saveTask(task);
        verify(taskRepository).save(task);
    }

    @Test
    public void taskNotFound() {
        given(taskRepository.findById(task.getId())).willReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, ()->{taskService.getTaskByIdWithThrow(task.getId());});

        verify(taskRepository, times(1)).findById(task.getId());

    }
}
