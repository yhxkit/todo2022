package com.example.demo22112802;

import com.example.demo22112802.dao.RoleRepository;
import com.example.demo22112802.dao.ToDoRepository;
import com.example.demo22112802.dao.UserRepository;
import com.example.demo22112802.model.Role;
import com.example.demo22112802.model.ToDo;
import com.example.demo22112802.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Demo22112802Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(Demo22112802Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Role admRole = new Role("ADMIN");
        Role guestRole = new Role("USER");

        roleRepository.saveAndFlush(admRole);
        roleRepository.saveAndFlush(guestRole);

        Users adm = new Users("admin@test.com", passwordEncoder.encode("1234"));
        Users user = new Users("tester@test.com", passwordEncoder.encode("1234"));


        adm.addAuthority(admRole);
        adm.addAuthority(guestRole);

        user.addAuthority(guestRole);

        userRepository.saveAndFlush(adm);
        userRepository.saveAndFlush(user);

        System.out.println("됐나요");
        System.out.println("그럼 이제 투두를");

        ToDo td = new ToDo(1, "투두1", "코멘트1", user);
        toDoRepository.saveAndFlush(td);

        ToDo td2 = new ToDo(2, "투두2", "코멘트2", user);
        toDoRepository.saveAndFlush(td2);

        System.out.println("모두 세이브");

//        userRepository.deleteUsersByUsername("tester");
//        System.out.println("유저 삭제");

    }
}
