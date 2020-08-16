package com.win.myawesometodoapp.Controller;

import com.win.myawesometodoapp.Model.User;
import com.win.myawesometodoapp.Payload.Request.LoginRequest;
import com.win.myawesometodoapp.Payload.Request.SignupRequest;
import com.win.myawesometodoapp.Payload.Response.JwtResponse;
import com.win.myawesometodoapp.Payload.Response.MessageResponse;
import com.win.myawesometodoapp.Repository.UserRepository;
import com.win.myawesometodoapp.Security.Jwt.JwtUtils;
import com.win.myawesometodoapp.Service.UserDetailServiceImp;
import com.win.myawesometodoapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

            User user = userRepository.findByUsername(loginRequest.getUsername());

            if(user == null ){
                return ResponseEntity.badRequest().body(new MessageResponse("Username or Password does not match"));
            }
            String password = userRepository.findByUsername(loginRequest.getUsername()).getPassword();
            Boolean matches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), password);
            if(!matches){
                return ResponseEntity.badRequest().body(new MessageResponse("Username or Password does not match"));
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = userRepository.findByUsername(userDetails.getUsername()).getId();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),roles,userId));
    }

    @PostMapping("/register")
    public ResponseEntity<?> userSignup(@RequestBody SignupRequest signupRequest){
        if (userService.findByUsername(signupRequest.getUsername()) != null){
            return ResponseEntity.badRequest().body(new MessageResponse("This username has been taken"));
        }
        userService.saveUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("Successfully register"));
    }
}
