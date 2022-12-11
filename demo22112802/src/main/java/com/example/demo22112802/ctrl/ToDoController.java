package com.example.demo22112802.ctrl;

import com.example.demo22112802.model.ToDo;
import com.example.demo22112802.service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ToDoController {


    @Autowired
    private ToDoServiceImpl toDoService;

    @RequestMapping("/todo/list")
    public String getToDoList(Model model, HttpServletRequest req){
        String userName = req.getUserPrincipal().getName();
        model.addAttribute("todos", toDoService.getToDoListByUser(userName));
        return "menu/toDoList";
    }
}
