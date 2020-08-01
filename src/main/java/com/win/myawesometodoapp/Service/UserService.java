package com.win.myawesometodoapp.Service;

import com.win.myawesometodoapp.Model.User;
import com.win.myawesometodoapp.Payload.Request.SignupRequest;

public interface UserService {

    void saveUser(SignupRequest signupRequest);

    User findByUsername(String username);

}
