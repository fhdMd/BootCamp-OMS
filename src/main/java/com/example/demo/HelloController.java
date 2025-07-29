package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }
}
