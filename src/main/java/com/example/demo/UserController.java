package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.model.UserRequestDTO;
import com.example.demo.model.UserResponseDTO;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;
    public UserController(UserRepo userRepo,KafkaTemplate kafkaTemplate,UserService userService){
        this.userRepo=userRepo;
        this.kafkaTemplate=kafkaTemplate;
        this.userService=userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO user){
        UserResponseDTO savedUserResponseDTO=userService.createUser(user);
        String message="user created with id"+savedUserResponseDTO.getId();
        kafkaTemplate.send("user.created",message);
        return savedUserResponseDTO;
    }
    
    @GetMapping
    public List<UserResponseDTO> getAll(){
        return userService.findAll();
    }
}
