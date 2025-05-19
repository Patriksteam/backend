package com.mycompany.backend;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorController {

    // Allgemeine Fehlerbehandlung (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorCode", "500");
        model.addAttribute("errorMessage", e.getMessage());
        System.out.println("Fehler aufgetreten:");
        e.printStackTrace();
        return "error";
    }

    // Fehlerbehandlung für 404 - Not Found
    @GetMapping("/error/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404Error(Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", "Seite nicht gefunden");
        return "error";
    }

    // Fehlerbehandlung für 403 - Forbidden
    @GetMapping("/error/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle403Error(Model model) {
        model.addAttribute("errorCode", "403");
        model.addAttribute("errorMessage", "Zugriff verweigert");
        return "error";
    }

    // Fehlerbehandlung für 401 - Unauthorized
    @GetMapping("/error/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handle401Error(Model model) {
        model.addAttribute("errorCode", "401");
        model.addAttribute("errorMessage", "Nicht autorisiert");
        return "error";
    }

    // Fallback für unbekannte Fehler
    @GetMapping("/error")
    public String handleGenericError(HttpServletRequest request, Model model) {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("errorCode", statusCode != null ? statusCode.toString() : "Unbekannt");
        model.addAttribute("errorMessage", "Ein unerwarteter Fehler ist aufgetreten");
        return "error";
    }
}


