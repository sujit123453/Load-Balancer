package com.bluethink.load_balancer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user")
    public String greet(String str){
        return "Hello! My name is Sujit Kumar";
    }
}
