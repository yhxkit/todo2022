package com.example.demo22112802.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController
{

    @RequestMapping("/main")
    public  String test(HttpServletRequest req){
        System.out.println("메인 컨트롤러에 안들어오나?");
        return "main";
    }

    @RequestMapping("/")
    public  String root(HttpServletRequest req){
        System.out.println("루트 컨트롤러에 안들어오나?");
        return "main";
    }


    @RequestMapping("/access_denied")
    public  String deny(){
        System.out.println("엑세스 거부");
        return "access_denied";
    }

    @RequestMapping("/login")
    public  String login(){
        System.out.println("로그인 컨트롤러에 안들어오나?");
        return "login";
    }

    @RequestMapping("/signup")
    public  String signup(){
        System.out.println("가입 컨트롤러에 안들어오나?");
        return "signup";
    }

}
