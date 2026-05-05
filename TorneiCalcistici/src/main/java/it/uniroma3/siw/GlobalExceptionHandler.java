package it.uniroma3.siw;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handle403(AccessDeniedException e) {
        return "error/403";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception e, Model model) {
        model.addAttribute("error", "Errore interno");
        return "error/500";
    }
    
    */
}