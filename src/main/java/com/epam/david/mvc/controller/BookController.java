package com.epam.david.mvc.controller;

import com.epam.david.mvc.entities.Book;
import com.epam.david.mvc.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    private BookService bookService;

    @RequestMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getById(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
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


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView postAddForm(@ModelAttribute("book") Book book) {
        Book insertedBook = bookService.insert(book);
        ModelAndView maw = new ModelAndView("book");
        maw.addObject("book", insertedBook);
        return maw;
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
