package com.win.myawesometodoapp.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
