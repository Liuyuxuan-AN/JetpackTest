package com.example.jetpacktest;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    private String name;
    private int pages;

    @PrimaryKey(autoGenerate = true)
    Long id;

    public void setName(String name) {
        this.name = name;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book(String name, int pages){
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }

    public Long getId() {
        return id;
    }
}
