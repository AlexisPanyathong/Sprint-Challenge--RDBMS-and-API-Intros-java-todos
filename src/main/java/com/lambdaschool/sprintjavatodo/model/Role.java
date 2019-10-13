package com.lambdaschool.sprintjavatodo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    String name;

    // Mapping over role to User Role
    @OneToMany(mappedBy = "role",
                cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userRoles = new ArrayList<>();

    // Empty Constructor
    public Role() {
    }

    // Constructor
    public Role(String name) {
        this.name = name;
    }

    // Getters and Setters

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public List<UserRoles> getUserroles() {
        return null;
    }
}