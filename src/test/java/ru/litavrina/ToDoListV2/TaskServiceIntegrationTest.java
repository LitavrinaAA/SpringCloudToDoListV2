package ru.litavrina.ToDoListV2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.litavrina.ToDoListV2.model.Task;
import ru.litavrina.ToDoListV2.repository.TaskRepository;
import ru.litavrina.ToDoListV2.service.TaskService;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class TaskServiceIntegrationTest {
    @Autowired
    private TaskService taskService;

    @MockBean
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
}
