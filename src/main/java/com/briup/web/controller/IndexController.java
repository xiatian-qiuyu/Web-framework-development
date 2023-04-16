package com.briup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/","/index","/toIndex"})
    public String toIndex(){
        return "index";
    }
    @GetMapping("/error")
    public String toError(){
        return "error";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @GetMapping("/toUserCenter")
    public String toUserCenter(){
        return "userinfo";
    }
}
