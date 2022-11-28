package com.example.demo22112802.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    public Authority(Users user, Role authority) {
        this.user = user;
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name="authority", nullable=false)
    private Role authority;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name="username")
    private Users user;

}
