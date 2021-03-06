package com.epam.david.service;

import com.epam.david.model.Author;
import com.epam.david.model.Book;
import com.epam.david.model.Genre;
import com.epam.david.repositories.AuthorRepository;
import com.epam.david.repositories.BookRepository;
import com.epam.david.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @PostConstruct
    public void init() {

    }

    @Transactional
    public void initData() {
        Book book = new Book();
        book.setName("Война и Мир");
        Author author = new Author();
        author.setName("Толстой");
        authorRepository.save(author);

        Genre genre = new Genre();
        genre.setName("Роман");
        genreRepository.save(genre);

        book.setAuthor(author);
        book.setAmount(10);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
//        if (id == null)
//            return null;
//        if (id == 0)
//            return null;
        try {
            bookService.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Book> getAll(Long skip, Long limit) {
        skip = skip == null ? 0 : skip;
        limit = limit == null ? 10 : limit;
        try {
            String sqlQueryString = "SELECT id, name, author FROM books OFFSET ? LIMIT ?    ";
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
//            book.setAuthor(String.valueOf(element.get("author")));
            return book;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book insert(Book book) {
        SimpleJdbcInsert insertOp = new SimpleJdbcInsert(jdbcTemplate).withTableName("books")
                .usingGeneratedKeyColumns("ID");
        Map<String, Object> paramsMap = new HashMap<>(2);
        paramsMap.put("name", book.getName());
        paramsMap.put("author", book.getAuthor());
        Number generatedId = insertOp.executeAndReturnKey(paramsMap);
        return getById(generatedId == null ? null : generatedId.longValue());
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) {
        String sqlQueryString = "UPDATE books SET name= ?, author = ? WHERE ID = ?";
        jdbcTemplate.update(sqlQueryString, new Object[]{book.getName(), book.getAuthor(), id});
        return getById(id);

    }

    @Override
    public void remove(Long id) {

    }
}
