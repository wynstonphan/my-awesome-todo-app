package com.win.myawesometodoapp.Service;

import com.win.myawesometodoapp.Model.Role;
import com.win.myawesometodoapp.Model.User;
import com.win.myawesometodoapp.Payload.Request.SignupRequest;
import com.win.myawesometodoapp.Repository.RoleRepository;
import com.win.myawesometodoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void saveUser(SignupRequest signupRequest) {
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        Role role = roleRepo.getOne((long) 2);
        roleSet.add(role);
        user.setUsername(signupRequest.getUsername());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.setRoles(roleSet);
        user.setTasks(null);
        userRepo.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
