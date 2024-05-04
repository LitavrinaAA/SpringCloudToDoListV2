package ru.litavrina.ToDoListV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.litavrina.ToDoListV2.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
