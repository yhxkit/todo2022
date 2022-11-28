package com.example.demo22112802.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController
{

    @RequestMapping("/test")
    public  String test(){
        return "/temp/test";
    }
}
