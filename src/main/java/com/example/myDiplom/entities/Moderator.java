package com.example.myDiplom.entities;

import jakarta.persistence.*;

@Entity
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(255)")
    private String login;
    @Column(columnDefinition = "varchar(255)")
    private String password;
    @Column(columnDefinition = "varchar(45) DEFAULT 'moderator'")
    private String role;
    @Column(columnDefinition = "tinyint(4) DEFAULT '1'")
    private String enabled;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
