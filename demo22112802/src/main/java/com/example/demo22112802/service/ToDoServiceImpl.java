package com.example.demo22112802.service;

import com.example.demo22112802.dao.ToDoRepository;
import com.example.demo22112802.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getToDoListByUser (String ownerName){
        return toDoRepository.getAllByOwnerUsername(ownerName);
    }

}
