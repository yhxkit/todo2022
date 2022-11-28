package com.example.demo22112802;

import com.example.demo22112802.dao.AuthorityRepository;
import com.example.demo22112802.dao.RoleRepository;
import com.example.demo22112802.dao.UserRepository;
import com.example.demo22112802.model.Role;
import com.example.demo22112802.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Demo22112802Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RoleRepository roleRepository;


    public static void main(String[] args) {
        SpringApplication.run(Demo22112802Application.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void run(String... args) throws Exception {

        Role admRole = new Role("ADMIN");
        Role guestRole = new Role("GUEST");

        roleRepository.saveAndFlush(admRole);
        roleRepository.saveAndFlush(guestRole);

        Users adm = new Users("admin", passwordEncoder().encode("1234"));
        Users user = new Users("tester", passwordEncoder().encode("1234"));


        adm.addAuthority(admRole);
        adm.addAuthority(guestRole);

        user.addAuthority(guestRole);

        userRepository.save(adm);
        userRepository.save(user);

        System.out.println("됐나요");

    }
}
