package com.epam.david.controller;

import com.epam.david.jms.MessageSender;
import com.epam.david.model.Book;
import com.epam.david.repositories.BookRepository;
import com.epam.david.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger("BookController");

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;
    @Autowired
    private MessageSender messageSender;

    @RequestMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getById(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookService.getById(id);
//        Book book = bookRepository.findOne(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "/jms/test", method = RequestMethod.GET)
    @ResponseBody
    public String jmsTest() {
//        messageSender.sendMessage(new Book(1L, "2", "3"));
        return "AAAAAAAAAAAAAAAAAAA";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public ModelAndView getByAuthor(@RequestParam("author") String author,
                                    @RequestHeader("Accept") String acceptType) {
        List<Book> books = bookService.getByAuthor(author);
        if ("application/pdf".equals(acceptType)) {
            return getPdfModelAndView(books);
        }
        return getHtmlModelAndView(books);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(value = "skip", defaultValue = "0") Long skip,
                               @RequestParam(value = "limit", defaultValue = "10") Long limit) {
        List<Book> books = bookService.getAll(skip, limit);
        return getHtmlModelAndView(books);
    }

    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public ModelAndView getAllFromRepo(@RequestParam(value = "skip", defaultValue = "0") Long skip,
                                       @RequestParam(value = "limit", defaultValue = "10") Long limit) {
        ModelAndView modelAndView = new ModelAndView("books");
        Specification<Book> bookSpecification = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.distinct(true);
                root.fetch("author", JoinType.LEFT);
                root.fetch("genre", JoinType.LEFT);
                return null;
            }
        };
        modelAndView.addObject("books", bookRepository.findAll(bookSpecification));
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getAddForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getById(id);
        book = book == null ? new Book() : book;
        model.addAttribute("book", book);
        model.addAttribute("id", id);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView postAddForm(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        Book insertedBook;
        if (!isIdAssigned(id)) {
            insertedBook = bookService.insert(book);
        } else
            insertedBook = bookService.update(id, book);

        ModelAndView maw = new ModelAndView("book");
        maw.addObject("book", insertedBook);
        return maw;
    }

    private boolean isIdAssigned(@PathVariable("id") Long id) {
        return id != 0;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void uploadBatch(@RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Book> books = mapper.readValue(file.getInputStream(), new TypeReference<List<Book>>() {
        });
        books.stream().forEach(book -> logger.info(book.getName()));
    }

    private ModelAndView getPdfModelAndView(List<Book> books) {
        return new ModelAndView("BookPd fView", "books", books);
    }

    private ModelAndView getHtmlModelAndView(List<Book> books) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

}
