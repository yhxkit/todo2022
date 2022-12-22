package com.example.demo22112802.ctrl;

import com.example.demo22112802.model.ToDo;
import com.example.demo22112802.model.ToDoList;
import com.example.demo22112802.model.Users;
import com.example.demo22112802.service.ToDoServiceImpl;
import com.example.demo22112802.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ToDoController {


    @Autowired
    private ToDoServiceImpl toDoService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/todo/list")
    public String getToDoList(Model model, HttpServletRequest req){
        String userName = req.getUserPrincipal().getName();
        model.addAttribute("todos", toDoService.getToDoListByUser(userName));
        return "menu/toDoList";
    }

    @RequestMapping("/todo/upload")
    public String saveToDoList(HttpServletRequest req, @RequestBody ToDoList list){
        String username = req.getUserPrincipal().getName();

        Users user = userService.getUserByName(username);


        toDoService.saveToDoList(user, list.getTodoList());
        return "menu/toDoList";
    }
}
