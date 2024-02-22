package com.nhnacademy.project.service;


import com.nhnacademy.project.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTask(Integer taskId);
    void createTask(Task task);
    void deleteTask(Integer taskId);

    void updateTask(Task task);
}
