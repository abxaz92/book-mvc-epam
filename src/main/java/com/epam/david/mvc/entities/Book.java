package com.epam.david.mvc.entities;

/**
 * Created by David_Chaava on 5/4/2017.
 */
public class Book {
    private Long id;
    private String name;
    private String author;

    public Book() {
    }

    public Book(Long id, String name, String author) {
        this();
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
