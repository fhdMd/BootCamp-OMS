package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserController(UserRepo userRepo,KafkaTemplate kafkaTemplate){
        this.userRepo=userRepo;
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        User savedUser=userRepo.save(user);
        String message="user created with id"+savedUser.getId();
        kafkaTemplate.send("user.created",message);
        return savedUser;
    }
    
    @GetMapping
    public List<User> getAll(){
        return userRepo.findAll();
    }
}
