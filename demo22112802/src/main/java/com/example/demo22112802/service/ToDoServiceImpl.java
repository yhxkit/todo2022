package com.example.demo22112802.service;

import com.example.demo22112802.dao.ToDoRepository;
import com.example.demo22112802.model.ToDo;
import com.example.demo22112802.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToDoServiceImpl {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getToDoListByUser (String ownerName){
        return toDoRepository.getAllByOwnerUsernameOrderByPriority(ownerName);
    }

    @Transactional
    public void saveToDoList (Users user, List<ToDo> toDoList){

        deleteToDoList(user.getUsername());

        for(ToDo td : toDoList){
            td.setOwner(user);
            System.out.println(user.getUsername() + ":" + td);
        }

        toDoRepository.saveAll(toDoList);
    }

    public void deleteToDoList (String ownerName){ toDoRepository.deleteAllByOwnerUsername(ownerName);}
}
