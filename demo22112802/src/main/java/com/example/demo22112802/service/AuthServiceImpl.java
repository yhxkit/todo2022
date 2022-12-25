package com.example.demo22112802.service;

import com.example.demo22112802.dao.AuthorityRepository;
import com.example.demo22112802.dao.RoleRepository;
import com.example.demo22112802.dao.UserRepository;
import com.example.demo22112802.model.Role;
import com.example.demo22112802.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }

    public List<Role> addRole(String roleName){

        Role role = new Role(roleName);

        if(!roleRepository.existsById(roleName)){
            roleRepository.save(role);
        }
        return getRoleList();
    }

    public void modUserAuth(String userName, List<Role> roleList){
        Users user = userRepository.getByUsername(userName);
        roleList.forEach(r -> user.addAuthority(r) );
        userRepository.save(user);
    }

}
