package com.shake_match.alchomist.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/oauth")
    public String login(){
        return "login/index";
    }
}
