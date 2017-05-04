package com.epam.david.mvc.controller;

import com.epam.david.mvc.common.OutputType;
import com.epam.david.mvc.entities.Book;
import com.epam.david.mvc.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = Logger.getLogger("BookController");
    @Autowired
    private BookService bookService;

    @RequestMapping("/{id}")
    public String test(@PathVariable(value = "id") String id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public ModelAndView getByAuthor(@RequestParam("author") String author,
                                    @RequestParam(value = "type", defaultValue = "HTML") OutputType outputType) {
        List<Book> books = bookService.getByAuthor(author);
        if (OutputType.PDF == outputType) {
            return getPdfModelAndView(books);
        }
        return getHtmlModelAndView(books);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void uploadBatch(@RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Book> books = mapper.readValue(file.getInputStream(), new TypeReference<List<Book>>() {
        });
        books.stream().forEach(book -> logger.info(book.getName()));
    }

    @RequestMapping("/error")
    public void testException() {
        throw new RuntimeException();
    }

    private ModelAndView getPdfModelAndView(List<Book> books) {
        return new ModelAndView("BookPdfView", "books", books);
    }

    private ModelAndView getHtmlModelAndView(List<Book> books) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

}
