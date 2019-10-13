package com.lambdaschool.sprintjavatodo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "userroles")
public class UserRoles extends Auditable implements Serializable {

    // Fetching user id
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    @JoinColumn(name = "userid")
    private User user;

    // Fetching role id
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"userRoles", "hibernateLazyInitializer"})
    @JoinColumn(name = "roleid")
    private Role role;

    // Need an empty/default Constructor
    public UserRoles() {
    }

    // Constructor
    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    // Getter and Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (!(o instanceof UserRoles)) {

            return false;
        }

        UserRoles userRoles = (UserRoles) o;
        return getUser().equals(userRoles.getUser()) && getRole().equals(userRoles.getRole());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getRole());
    }

    @Override
    public List<UserRoles> getUserroles() {
        return null;
    }
}
