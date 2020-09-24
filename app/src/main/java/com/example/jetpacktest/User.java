package com.example.jetpacktest;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 定义封装实际数据的实体类
 * 会对应数据库中的一张表，表中的字段根据类自动生成
 */
@Entity
public class User {
    private String firstName;
    private String lastName;
    private int age;

    @PrimaryKey (autoGenerate = true)
    Long id;

    public User(String firstName,String lastName,int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }
}
