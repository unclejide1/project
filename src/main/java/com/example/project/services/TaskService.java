package com.example.project.services;

import com.example.project.models.Task;
import com.example.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        Task t = null;
        try{
            t = taskRepository.save(task);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }

    public Task getATask(Long id){
        return taskRepository.getOne(id);
    }
}
