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
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "/employee/advanced/hello";
    }
}
