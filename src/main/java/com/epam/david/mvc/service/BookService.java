package com.epam.david.mvc.service;

import com.epam.david.mvc.entities.Book;

import java.util.List;

/**
 * Created by David_Chaava on 5/4/2017.
 */
public interface BookService {
    Book getById(String id);

    List<Book> getByAuthor(String author);
}
