package com.win.myawesometodoapp.Service;


import com.win.myawesometodoapp.DTO.TaskDTO;
import com.win.myawesometodoapp.Model.Task;
import com.win.myawesometodoapp.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Collection;


@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    private TaskRepository taskRepo;


    @Override
    public Collection<Task> findByUserId(Long userId) {
        return taskRepo.findByUserId(userId);
    }

    @Override
    public Task addTask(Long id, TaskDTO taskDTO) {
        Task task = new Task();
        task.setComplete(taskDTO.getComplete());
        task.setText(taskDTO.getText());
        task.setDateSubmit(taskDTO.getSubmitDate());
        task.setUserId(id);
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if(taskRepo.findById(task.getId()) != null)
            return taskRepo.save(task);
        return null;
    }

    @Override
    public Task deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
        return null;
    }
}
