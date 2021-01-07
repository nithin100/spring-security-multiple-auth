package com.nithin.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @GetMapping("/hello")
    public String helloMobile(){
        return "Hello Mobile";
    }

}
