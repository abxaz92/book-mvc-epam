package com.epam.david.mvc.service;

import com.epam.david.mvc.entities.Book;

import java.util.List;

/**
 * Created by David_Chaava on 5/4/2017.
 */
public interface BookService {
    Book getById(Long id);

    List<Book> getAll(Long skip, Long limit);

    List<Book> getByAuthor(String author);

    Book insert(Book book);

    void remove(Long id);

    Book update(Long id, Book book);

    void initData();
}
