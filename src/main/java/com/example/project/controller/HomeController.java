package com.example.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> Home(){
        List<String> li = new ArrayList<String>();
        li.add("Welcome to me");
        li.add("this awesome powered");
        li.add("AI/Hello todo ok App");
        return li;
    }
}
