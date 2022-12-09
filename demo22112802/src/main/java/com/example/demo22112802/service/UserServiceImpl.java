package com.example.demo22112802.service;

import com.example.demo22112802.dao.AuthorityRepository;
import com.example.demo22112802.dao.RoleRepository;
import com.example.demo22112802.dao.UserRepository;
import com.example.demo22112802.model.Users;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository,  RoleRepository roleRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 시도 >> "+ username);
        Users user = userRepository.getByUsername(username);


        if (!Optional.ofNullable(user).isPresent()) {
            log.info("없는 계정입니다.");
            throw new UsernameNotFoundException("유저 이름을 못찾겠다.");
        }
        if(user.getDeleteAt() != null){
            log.info("삭제된 계정입니다.");
            throw new RuntimeException("삭제된 계정입니다");
        }
        if(!user.isEnabled()){
            log.info("정지된 계정입니다.");
            throw new RuntimeException("정지된 계정입니다");
        }


        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getAuthorities().forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority().getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

    }
}
