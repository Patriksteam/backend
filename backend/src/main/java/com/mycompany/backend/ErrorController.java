package com.mycompany.backend;  // Dein Package entsprechend anpassen



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {

    // Fehlerbehandlung für allgemeine Ausnahmen
    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorCode", "500");  // Beispiel für Fehlercode 500
        model.addAttribute("errorMessage", e.getMessage());  // Fehlernachricht
        return "error";  // Fehlerseite wird angezeigt
    }

    // Fehlerbehandlung für 404 (Seite nicht gefunden)
    @GetMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle404Error(Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", "Seite nicht gefunden");
        return "error";  // Fehlerseite für 404
    }
}

