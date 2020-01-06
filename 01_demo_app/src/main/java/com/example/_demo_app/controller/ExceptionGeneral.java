package com.example._demo_app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral {

    // Az összes kontrollerben dobott exceptionöket elkapja
    @ExceptionHandler
    public String exception(Exception ex, Model model){
        model.addAttribute("exception", ex);
        return "whateverHandlesTheException";
    }
}
