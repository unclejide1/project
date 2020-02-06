package com.example.project.controller;

import com.example.project.models.Task;
import com.example.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String path;

    @Autowired
    private TaskService taskService;
    @GetMapping()
    public  String healthCheck(){
        return "our app is running on port:" + port + " & path: " + path;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task t = taskService.createTask(task);
        HttpStatus statusCode = HttpStatus.CREATED;
        if(t == null){
            statusCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(t, statusCode);
    }
}
