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
    private Map<String, Book> booksMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        booksMap.put("1", new Book(1L, "Война и Мир", "Толстой"));
        booksMap.put("2", new Book(2L, "Мастер и Маргарита", "Булгаков"));
        booksMap.put("3", new Book(3L, "После Бала", "Толстой"));
    }

    @Override
    public Book getById(String id) {
        try {
            String sqlQueryString = "SELECT id, name, author FROM books WHERE ID = ?";
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sqlQueryString, new Object[]{1L});
            Book book = jdbcTemplate
                    .queryForObject(sqlQueryString, new Object[]{1L}, (resultSet, i) ->
                            new Book(resultSet.getLong("ID"), resultSet.getString("name"), ""));

            logger.warn("{}", res);
            logger.warn("{}", book != null ? book.getName() : "NULL");
            return booksMap.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
