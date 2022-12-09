package com.example.demo22112802.dao;

import com.example.demo22112802.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
