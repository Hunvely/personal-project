package com.rod.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String hello() {
        String str = homeService.test();
        return "Welcome To Spring Boot ! " + str;
    }

    @PostMapping("/name")
    public Map<String, ?> name(@RequestBody Map<String, ?> map) {
        String name = (String) map.get("name");
        System.out.println("리퀘스트가 가져온 이름 : " + name);
        Map<String, String> respMap = new HashMap<>();
        respMap.put("name", "환영합니다 " + name);
        return respMap;
    }
}
