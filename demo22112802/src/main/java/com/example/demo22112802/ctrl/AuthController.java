package com.example.demo22112802.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController
{

    @RequestMapping("/main")
    public  String test(){
        System.out.println("메인 컨트롤러에 안들어오나?");
        return "main";
    }


    @RequestMapping("/login")
    public  String login(){
        System.out.println("로그인 컨트롤러에 안들어오나?");
        return "login";
    }
}
