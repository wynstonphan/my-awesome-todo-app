package com.win.myawesometodoapp.Controller;

import com.win.myawesometodoapp.DTO.TaskDTO;
import com.win.myawesometodoapp.Model.Task;
import com.win.myawesometodoapp.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}/tasks")
    ResponseEntity<Collection<Task>> getUserTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/task")
    ResponseEntity<Task> userAddTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.addTask(id,taskDTO),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/task")
    ResponseEntity<?> userUpdaterTask(@PathVariable Long id, @RequestBody Task task){
        if (task.getUserId() != id){
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(taskService.updateTask(id,task),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/task/{taskId}")
    ResponseEntity<?> userDeleteTask(@PathVariable Long id, @PathVariable Long taskId){
        return new ResponseEntity<>(taskService.deleteTask(taskId),HttpStatus.OK);
    }
}
