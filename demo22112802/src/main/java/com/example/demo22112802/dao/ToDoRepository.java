package com.example.demo22112802.dao;

import com.example.demo22112802.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> getAllByOwnerUsernameOrderByPriority(String ownerName);
    List<ToDo> getAllOrOrderByIdxDesc();
    void deleteAllByOwnerUsername(String ownerName);
}
