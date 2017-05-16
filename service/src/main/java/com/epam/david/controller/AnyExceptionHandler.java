package com.epam.david.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@ControllerAdvice
public class AnyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("cause", e.toString());
        return modelAndView;
    }
}
