package com.example.demo22112802.ctrl;

import com.example.demo22112802.model.Role;
import com.example.demo22112802.model.ToDo;
import com.example.demo22112802.model.Users;
import com.example.demo22112802.service.AuthServiceImpl;
import com.example.demo22112802.service.ToDoServiceImpl;
import com.example.demo22112802.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/admin")
public class AdminController {

    @Autowired
    ToDoServiceImpl toDoService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthServiceImpl authService;

    @RequestMapping("/todos")
    public List<ToDo> getAllToDoList(){
        return toDoService.getAllToDoList();
    }

    @RequestMapping("/todos/{userName}")
    public List<ToDo> getUserToDoList(@RequestParam String userName){
        return toDoService.getToDoListByUser(userName);
    }

    @RequestMapping("/users")
    public List<Users> getUserList()
    {
        return userService.getUserList();
    }

    @RequestMapping("/users/{userName}")
    public Users getUserByName(@RequestParam String userName){
        return userService.getUserByName(userName);
    }

    @RequestMapping("/users/{userName}/modauth")
    public Users modAuth(@RequestParam String userName, List<Role> roleList){
        authService.modUserAuth(userName, roleList);
        return getUserByName(userName);
    }


    @RequestMapping("/roles")
    public List<Role> getRoleList(){
        return authService.getRoleList();
    }

    @RequestMapping("/roles/add/{roleName}")
    public List<Role> addRole(@RequestParam String roleName){
        authService.addRole(roleName);
        return authService.getRoleList();
    }

}
