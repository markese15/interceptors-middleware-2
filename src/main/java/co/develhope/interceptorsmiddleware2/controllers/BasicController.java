package co.develhope.interceptorsmiddleware2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

    @GetMapping
    public String getWelcome(){
        return "Welcome on this page!";
    }


}