package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, Object> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from DigitalOcean App Platform (Spring Boot)! Hi");
        response.put("timestamp", new Date());
        return response;
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
