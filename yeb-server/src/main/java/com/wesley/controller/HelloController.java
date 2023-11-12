package com.wesley.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    @GetMapping("hello")
    public String hello(){
        System.out.println("here");
        System.out.println(secret);
        System.out.println(expiration);
        return "hello";
    }
}
