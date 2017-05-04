package com.epam.david.mvc.service;

import com.epam.david.mvc.entities.Book;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Service
public class BookServiceImpl implements BookService {
    private Map<String, Book> booksMap = new HashMap<>();

    @PostConstruct
    public void init() {
        booksMap.put("1", new Book("1", "Война и Мир", "Толстой"));
        booksMap.put("2", new Book("2", "Мастер и Маргарита", "Булгаков"));
        booksMap.put("3", new Book("3", "После Бала", "Толстой"));
    }

    @Override
    public Book getById(String id) {
        return booksMap.get(id);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return booksMap
                .values()
                .stream()
                .filter(book -> Objects.equals(book.getAuthor(), author))
                .collect(Collectors.toList());

    }
}
