package com.example.demo22112802.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @Column(nullable = false)
    private String roleName;
}
