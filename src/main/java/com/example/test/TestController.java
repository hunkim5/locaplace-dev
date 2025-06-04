package com.example.test;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return new String("test11");
    }
    
}
