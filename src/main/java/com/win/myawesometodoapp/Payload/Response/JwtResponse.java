package com.win.myawesometodoapp.Payload.Response;

import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {


    private String token;
    private String type = "Bearer";

    private String username;
    private List<String> roles;

    public JwtResponse(String token,  String username, List<String> roles) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
        
    }


}
