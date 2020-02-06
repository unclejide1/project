package com.example.project.services;

import com.example.project.models.Task;
import com.example.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
}
