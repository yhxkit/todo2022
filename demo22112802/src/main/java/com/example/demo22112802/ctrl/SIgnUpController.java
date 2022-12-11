package com.example.demo22112802.ctrl;

import com.example.demo22112802.model.SignUpResult;
import com.example.demo22112802.model.Users;
import com.example.demo22112802.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SIgnUpController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public SignUpResult signup(@RequestBody Users user){
        return userService.signUpUser(user);
    }

    @PostMapping("/validateAccount")
    public boolean valiadCheck(@RequestBody Users user){
        return !userService.checkDuplicatedAccount(user.getUsername());
    }
}
