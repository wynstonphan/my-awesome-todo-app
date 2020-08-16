package com.win.myawesometodoapp.Service;


import com.win.myawesometodoapp.DTO.TaskDTO;
import com.win.myawesometodoapp.Model.Task;
import com.win.myawesometodoapp.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


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
        Date today = Calendar.getInstance().getTime();
        Task task = new Task();
        task.setComplete(false);
        task.setText(taskDTO.getText());
        task.setDateSubmit(today);
        task.setUserId(id);
        return taskRepo.save(task);
    }

    @Override
    public Task completeTask(Long taskId) {
        Task task = taskRepo.getOne(taskId);
        task.setComplete(true);
        return taskRepo.save(task);

    }

    @Override
    public Task deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
        return null;
    }
}
