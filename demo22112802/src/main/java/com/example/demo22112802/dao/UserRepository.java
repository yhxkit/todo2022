package com.example.demo22112802.dao;

import com.example.demo22112802.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Users, String> {

    Users getByUsername(String username);
}
