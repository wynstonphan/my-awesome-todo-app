package com.win.myawesometodoapp.Payload.Request;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequest {

    private String username;

    private String password;
}
