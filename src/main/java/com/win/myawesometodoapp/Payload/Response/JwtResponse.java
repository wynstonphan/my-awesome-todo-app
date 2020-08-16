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
    private Long userId;

    public JwtResponse(String token,  String username, List<String> roles,Long userId) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
        this.userId = userId;
        
    }


}
