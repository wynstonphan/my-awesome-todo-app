package com.win.myawesometodoapp.Payload.Request;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {

    private String username;

    private String password;
}
