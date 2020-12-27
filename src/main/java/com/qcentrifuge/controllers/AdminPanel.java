package com.qcentrifuge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanel {

    @GetMapping
    public String getAdmin(){
        return "admin";
    }


    @GetMapping("/panel")
    public String getAdminPanel(){
        return "apanel";
    }

}
