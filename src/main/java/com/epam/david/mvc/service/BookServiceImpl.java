package com.epam.david.mvc.service;

import com.epam.david.mvc.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
    }

    @Override
    public Book getById(Long id) {
        try {
            String sqlQueryString = "SELECT id, name, author FROM books WHERE ID = ?";
            Book book = jdbcTemplate
                    .queryForObject(sqlQueryString, new Object[]{id}, (resultSet, i) -> {
                        long bookId = resultSet.getLong("ID");
                        String name = resultSet.getString("name");
                        String author = resultSet.getString("author");
                        return new Book(bookId, name, author);
                    });
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> getAll(Long skip, Long limit) {
        skip = skip == null ? 0 : skip;
        limit = limit == null ? 10 : limit;
        try {
            logger.warn("{}", limit);
            String sqlQueryString = "SELECT id, name, author FROM books OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
            List<Map<String, Object>> booksMap = jdbcTemplate.queryForList(sqlQueryString, new Object[]{skip, limit});
            List<Book> books = mapToList(booksMap);
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> getByAuthor(String author) {
        try {
            String sqlQueryString = "SELECT id, name, author FROM books WHERE AUTHOR = ?";
            List<Map<String, Object>> booksMap = jdbcTemplate.queryForList(sqlQueryString, new Object[]{author});
            List<Book> books = mapToList(booksMap);
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Book> mapToList(List<Map<String, Object>> booksMap) {
        return booksMap.stream().map(element -> {
            Book book = new Book();
            book.setId(Long.valueOf(String.valueOf(element.get("ID"))));
            book.setName(String.valueOf(element.get("name")));
            book.setAuthor(String.valueOf(element.get("author")));
            return book;
        }).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Book update(Long id, Book book) {
        return null;
    }
}
