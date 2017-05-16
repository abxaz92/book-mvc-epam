package com.epam.david.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
