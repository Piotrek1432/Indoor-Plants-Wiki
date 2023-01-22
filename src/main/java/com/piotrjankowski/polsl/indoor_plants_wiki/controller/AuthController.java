package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.AuthCredencialsRequest;
import com.piotrjankowski.polsl.indoor_plants_wiki.logic.JwtUtil;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.UserRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.LoginAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    public ResponseEntity<?> login (@RequestBody AuthCredencialsRequest request){
        return getResponseEntity(request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public ResponseEntity<?> register (@RequestBody AuthCredencialsRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole("ROLE_USER");
        userRepository.save(newUser);

        return getResponseEntity(request);
    }

    private ResponseEntity<?> getResponseEntity(@RequestBody AuthCredencialsRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            LoginAnswer loginAnswer = new LoginAnswer();
            loginAnswer.setAnswer(jwtUtil.generateToken(user));
            loginAnswer.setRole(user.getRole());
            return ResponseEntity.ok()
                    .body(loginAnswer);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
