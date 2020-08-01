package com.win.myawesometodoapp.Controller;

import com.win.myawesometodoapp.Payload.Request.LoginRequest;
import com.win.myawesometodoapp.Payload.Request.SignupRequest;
import com.win.myawesometodoapp.Payload.Response.JwtResponse;
import com.win.myawesometodoapp.Payload.Response.MessageResponse;
import com.win.myawesometodoapp.Repository.UserRepository;
import com.win.myawesometodoapp.Security.Jwt.JwtUtils;
import com.win.myawesometodoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> userSignup(@RequestBody SignupRequest signupRequest){
        if (userService.findByUsername(signupRequest.getUsername()) != null){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
        }
        userService.saveUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("Successfully register"));
    }
}
