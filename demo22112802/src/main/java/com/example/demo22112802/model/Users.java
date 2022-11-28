package com.example.demo22112802.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@ToString(exclude = {"authorities"})
@NoArgsConstructor
public class Users {
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Users(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime deleteAt = null;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    List<Authority> authorities;



    public void addAuthority(Role authority) {
        if (!Optional.ofNullable(authorities).isPresent()) {
            authorities = new ArrayList<>();
        }
        authorities.add(new Authority(this, authority));
    }
}
