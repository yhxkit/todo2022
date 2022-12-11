package com.example.demo22112802.dao;

import com.example.demo22112802.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository  extends JpaRepository<Users, String> {

    Users getByUsername(String username);

    @Transactional
    void deleteUsersByUsername(String username);

     boolean existsUsersByUsername(String username);
}
