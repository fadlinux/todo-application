package com.todo.task.application.repository;

import com.todo.task.application.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    public Task findTaskById (Long Id);
}
