package com.epam.david.mvc.repositories;

import com.epam.david.mvc.entities.Author;
import com.epam.david.mvc.entities.Book;
import com.epam.david.mvc.entities.Genre;
import com.epam.david.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by David_Chaava on 5/16/2017.
 */
@Service
public class InitTestDbDataService {

    @Autowired
    private BookService bookService;

    @PostConstruct
    public void init() {
        bookService.initData();
    }
}
