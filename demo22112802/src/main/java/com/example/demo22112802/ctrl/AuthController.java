package com.example.demo22112802.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController
{

    @RequestMapping("/main")
    public  String test(HttpServletRequest req){
        return "main";
    }

    @RequestMapping("/")
    public  String root(HttpServletRequest req){
        return "main";
    }


    @RequestMapping("/access_denied")
    public  String deny(){
        return "access_denied";
    }

    @RequestMapping("/login")
    public  String login(){
        return "login";
    }

    @GetMapping("/signup")
    public  String signup(){
        return "signup";
    }

}
