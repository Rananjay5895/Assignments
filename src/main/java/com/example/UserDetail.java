package com.example;

import jakarta.inject.Singleton;

import javax.persistence.*;

@Entity(name="UserDetail")
public class UserDetail {
    public UserDetail() {
    }

    public UserDetail(String name) {
        this.name = name;
    }

    public UserDetail(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
