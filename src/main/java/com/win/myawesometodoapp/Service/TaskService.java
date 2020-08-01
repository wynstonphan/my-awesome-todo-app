package com.win.myawesometodoapp.Service;

import com.win.myawesometodoapp.DTO.TaskDTO;
import com.win.myawesometodoapp.Model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Collection<Task> findByUserId(Long userId);

    Task addTask(Long id, TaskDTO taskDTO);

    Task updateTask(Long id, Task task);

    Task deleteTask(Long taskId);
}
