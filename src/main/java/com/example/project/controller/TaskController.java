package com.example.project.controller;

import com.example.project.models.Task;
import com.example.project.services.TaskService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public MyResponse<Task> createTask(@RequestBody Task task, HttpServletResponse response){
        Task t = taskService.createTask(task);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Todo created successfully";
        if(t == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "common get out with your bad request!!!";
        }
        response.setStatus(statusCode.value());
        return new MyResponse<>(statusCode, message, t);
    }
    @GetMapping
    @RequestMapping("{id}")
    public  MyResponse<Task> getOneTask(@PathVariable Long id){
        Task t = taskService.getATask(id);
        if(t == null){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task with this id: " + id, null);
        }
        return  new MyResponse<>(HttpStatus.OK, "Retrieved Successfully", t);
    }

    @GetMapping
    @RequestMapping("/all")
    public MyResponse<List<Task>> getAllTask(){
        List<Task> t = taskService.getAllTask();
        if(t.isEmpty()){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task Available", null);
        }
        return  new MyResponse<>(HttpStatus.OK, "Retrieved Successfully", t);
    }

    @GetMapping
    @RequestMapping("/Status/{status}")
    public MyResponse<List<Task>> findBystatus(@PathVariable String status){
        List<Task> t = taskService.findByStatus(status);
        if(t.isEmpty()){
            return new MyResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "No task Available", null);
        }
        return  new MyResponse<>(HttpStatus.OK, "Retrieved Successfully", t);
    }
}
