package com.meiliev.database.entity;

import java.util.Set;


public class User {


    private Long id;
    private String username;
    private String password;

    private Set<Article> articles;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' + '}';
    }
}
