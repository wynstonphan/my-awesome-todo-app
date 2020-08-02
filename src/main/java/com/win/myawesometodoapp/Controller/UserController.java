package com.win.myawesometodoapp.Controller;

import com.win.myawesometodoapp.DTO.TaskDTO;
import com.win.myawesometodoapp.Model.Task;
import com.win.myawesometodoapp.Payload.Response.MessageResponse;
import com.win.myawesometodoapp.Repository.UserRepository;
import com.win.myawesometodoapp.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepo;

    private Boolean isAuthorized(Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        System.out.println(username);
        if(userRepo.findById(id).get().getUsername().equals(username))
            return true;
        return false;
    }

    @GetMapping("/{id}/tasks")
    ResponseEntity<?> getUserTask(@PathVariable Long id){
        if(!isAuthorized(id)){
            return ResponseEntity.status(403).body(new MessageResponse("You're trying to access to other user"));
        }
        return new ResponseEntity<>(taskService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/task")
    ResponseEntity<?> userAddTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        if(!isAuthorized(id)){
            return ResponseEntity.status(403).body(new MessageResponse("You're trying to access to other user"));
        }
        return new ResponseEntity<>(taskService.addTask(id,taskDTO),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/task")
    ResponseEntity<?> userUpdaterTask(@PathVariable Long id, @RequestBody Task task){
        if(!isAuthorized(id)){
            return ResponseEntity.status(403).body(new MessageResponse("You're trying to access to other user"));
        } else{
        if (task.getUserId() != id){
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(new MessageResponse("This task is not belong to you"));
        }
        return new ResponseEntity<>(taskService.updateTask(id,task),HttpStatus.ACCEPTED);}
    }

    @DeleteMapping("/{id}/task/{taskId}")
    ResponseEntity<?> userDeleteTask(@PathVariable Long id, @PathVariable Long taskId){
        if(!isAuthorized(id)){
            return ResponseEntity.status(403).body(new MessageResponse("You're trying to access to other user"));
        }
        return new ResponseEntity<>(taskService.deleteTask(taskId),HttpStatus.OK);
    }
}
