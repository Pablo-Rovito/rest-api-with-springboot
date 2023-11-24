package com.techu.apitechu.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    // por convención se usa index para url "/"
    public String index() {
        return "Hola Mundo";
    }

    @RequestMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "Tech U") String name
    ) {
        return String.format("Hola %s", name);
    }
}
