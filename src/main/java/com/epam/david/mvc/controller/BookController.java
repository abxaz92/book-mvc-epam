package com.epam.david.mvc.controller;

import com.epam.david.mvc.common.OutputType;
import com.epam.david.mvc.entities.Book;
import com.epam.david.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/{id}")
    public String test(@PathVariable(value = "id") String id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @RequestMapping(path = "/author", method = RequestMethod.GET)
    public ModelAndView getByAuthor(@RequestParam("author") String author,
                                    @RequestParam(value = "type", defaultValue = "HTML") OutputType outputType) {
        List<Book> books = bookService.getByAuthor(author);
        if (OutputType.PDF == outputType) {
            return new ModelAndView("BookPdfView", "books", books);
        }
        return getHtmlModelAndView(books);
    }

    private ModelAndView getHtmlModelAndView(List<Book> books) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/error")
    public void testException() {
        throw new RuntimeException();
    }

}
