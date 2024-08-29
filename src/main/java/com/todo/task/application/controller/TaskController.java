package com.todo.task.application.controller;

import com.todo.task.application.model.Task;
import com.todo.task.application.repository.TaskRepository;
import com.todo.task.application.vo.TaskInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //[GET] /tasks : get all task list
    @GetMapping
    public ResponseEntity getAll(){
        List<Task> listOfTask = taskRepository.findAll();
        return ResponseEntity.ok(listOfTask);
    }

    //[GET] /tasks/id?id=xxx : get detail task by id
    @GetMapping("/id")
    public ResponseEntity list(@RequestParam(name="id",required = true) Long id){
        Task taskById = taskRepository.findTaskById(id);
        return ResponseEntity.ok(taskById);
    }

    //[POST] /tasks : post data task list
    @PostMapping
    public ResponseEntity create(@RequestBody TaskInput reqBody){
        Task newTasks = new Task();

        newTasks.setTitle(reqBody.getTitle());
        newTasks.setDescription(reqBody.getDescription());
        newTasks.setStatus(reqBody.getStatus());

        taskRepository.save(newTasks);

        return ResponseEntity.ok("success create task");
    }

    //[PUT] /tasks : update data task list by id
    @PutMapping
    public ResponseEntity update(@RequestBody TaskInput reqBody){
        Task newTask = new Task();

        Task todoById = taskRepository.findTaskById(reqBody.getId());

        todoById.setTitle(reqBody.getTitle());
        todoById.setDescription(reqBody.getDescription());
        todoById.setStatus(reqBody.getStatus());

        String msgBody = "Error, data not found!!";
        if (todoById.getId() != 0) {
            taskRepository.save(todoById);
            msgBody = "success update task";
        }

        return ResponseEntity.ok(msgBody);
    }

    //[DELETE] /tasks/id?id=xxx : delete data task list by id
    @DeleteMapping("/id")
    public ResponseEntity delete(@RequestParam(name="id",required = true) Long id){
        taskRepository.deleteById(id);
        return ResponseEntity.ok("success delete task id : " + id);
    }
}

