package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserRequestDTO;
import com.example.demo.model.UserResponseDTO;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepo userRepo,PasswordEncoder encoder){
        this.userRepo=userRepo;
        this.encoder=encoder;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO user){
        User savedUser=new User();
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(savedUser);
        return new UserResponseDTO(savedUser);
    }
    @Transactional
    public List<UserResponseDTO> findAll(){
        return userRepo.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }
}
